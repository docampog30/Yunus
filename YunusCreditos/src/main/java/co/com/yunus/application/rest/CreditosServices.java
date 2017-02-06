package co.com.yunus.application.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Aporte;
import co.com.yunus.application.dto.Cliente;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;
import co.com.yunus.application.dto.RequestCredito;
import co.com.yunus.application.dto.RequestLiquidarCuota;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.infrastructure.reports.ReportGenerator;
import co.com.yunus.infrastructure.util.MailSender;

@Path("creditos")
public class CreditosServices {

	@Inject
	private ICreditosRepository creditosRepository;
	
	@Inject
	@Named("ClienteRepositoryImpl")
	private IClientesRepository clientesRepository;
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private ReportGenerator reportGenerator;
	
	@Inject
	private MailSender sender;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] guardarCredito(RequestCredito request){
		Credito credito = new Credito();
		credito.setFecha(new Date());
		credito.setIdcliente(request.getIdcliente());
		credito.setInteres(request.getInteres());
		credito.setPlazo(request.getPlazo());
		credito.setValor(request.getValor().floatValue());
		credito.setDetalles(request.getDetalles());
		credito.setIdmaestro(request.getIdmaestro());
		credito.getDetalles().forEach(d-> {
		   d.setEstado("PENDIENTE");
		   d.setCredito(credito);
		   });
		
		creditosRepository.guardarCreditoCliente(credito);
		credito.getDetalles().forEach(d-> d.setCredito(null));
		Cliente cliente = clientesRepository.getClientByID((long) request.getIdcliente()).get(0);
		return reportGenerator.getBytes(getParametersCreditos(credito,cliente), "amortizacionCreditos.jasper",credito.getDetalles());
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findByCliente/{cedula}")
	public List<Credito> findByCliente(@PathParam("cedula")String cedula){
		List<Credito> credito = creditosRepository.findByCliente(cedula);
		credito.forEach(d-> d.getDetalles().forEach(c-> c.setCredito(null)));
		return credito;
	}
	
	@POST
	@Path("aporte")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] liquidarAporte(RequestCredito request){
		Aporte aporte = new Aporte();
		aporte.setFecha(new Date());
		aporte.setIdcliente(request.getIdcliente());
		aporte.setValor(request.getValor());
		aporte.setTipo(request.getTipaporte());
		creditosRepository.guardarAporte(aporte);
		Cliente cliente = getCliente((long) request.getIdcliente());
		aporte.setCliente(cliente);
		

		Map<String, Object> parametersAporte = getParametersAporte(aporte);
		sender.enviarMailAbono(cliente.getEmail(),"Comprobante aporte COOPECEJA",getMailContentAporte(parametersAporte));
		
		
		return reportGenerator.getBytes(parametersAporte, "recibosAportes.jasper");
	}
	
	private String getMailContentAporte(Map<String, Object> parametersAporte) {
		String content = "Señor(a) \n%s %s\n\nUsted acaba de generar un aporte por valor de: %s.\n\nPara COOPECEJA es un gusto poderle prestar nuestros mejores servicios y proporcionales esta información como evidencia de su pago.\n\nAgradecemos su atención.\n\nCOOPECEJA";
		
		Aporte aporte =  (Aporte) parametersAporte.get("aporte");
		return String.format(content,aporte.getCliente().getNombres(),aporte.getCliente().getApellidos(),aporte.getValor());
	}

	@POST
	@Path("liquidar")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] liquidarCuotas(RequestLiquidarCuota request){
		request.getDetalles().forEach(d->{d.setEstado("PAGADA");
										d.setFechapago(new Date());	
		});
		creditosRepository.liquidarCuotas(request.getDetalles());
		Cliente cliente = getCliente((long) request.getIdcliente());
		
		Map<String, Object> parametrosAbonos = getParametrosAbonos(cliente,request);
		
		sender.enviarMailAbono(cliente.getEmail(),String.format("Abono al credito # %s COOPECEJA, %s %s", request.getIdcredito(),cliente.getNombres(),cliente.getApellidos()),getMailContent(parametrosAbonos));
		
		
		return reportGenerator.getBytes(parametrosAbonos, "recibosAbonosCreditos.jasper");
	}
	
	@POST
	@Path("sum/{feini}/{fefin}")
	public BigDecimal getSum(@PathParam("feini")Date feIni,@PathParam("fefin")Date feFin){
		return creditosRepository.findSumBetweenDates(feIni,feFin);
	}

	private Cliente getCliente(Long id) {
		return clientesRepository.getClientByID(id).get(0);
	}

	private Map<String, Object> getParametersAporte(Aporte aporte) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("aporte", aporte);
		return parameters;
	}
	
	private Map<String, Object> getParametersCreditos(Credito credito, Cliente cliente) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("credito", credito);
		parameters.put("cliente", cliente);
		return parameters;
	}
	
	private String getMailContent(Map<String, Object> parametrosAbonos) {
		String content = "Señor(a) \n%s %s\n\nUsted acaba de generar el abono al credito # %s de %s cuota(s) por valor de: %s.\nPara COOPECEJA es un gusto poderle prestar nuestros mejores servicios y proporcionales esta información como evidencia de su pago.\n\nAgradecemos su atención.\n\nCOOPECEJA";
		
		Cliente cliente =  (Cliente) parametrosAbonos.get("cliente");
		return String.format(content,cliente.getNombres(),cliente.getApellidos(),parametrosAbonos.get("credito"),parametrosAbonos.get("cuotas"),parametrosAbonos.get("valor"));
	}

	private Map<String, Object> getParametrosAbonos(Cliente cliente, RequestLiquidarCuota request) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("credito", request.getIdcredito());
		parameters.put("cliente", cliente);
		parameters.put("cuotas", request.getDetalles().size());
		parameters.put("saldo", request.getDetalles().stream()
                .max((p1, p2) -> Integer.compare( p1.getPeriodo(), p2.getPeriodo()))
                .get().getSaldofinal());
		parameters.put("valor", request.getDetalles()
								.stream()
								 .map(Detalle::getCuota)
							        .reduce(BigDecimal.ZERO, BigDecimal::add));
		return parameters;
	}
}
