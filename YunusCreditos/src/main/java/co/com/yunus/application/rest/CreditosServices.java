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
	ReportGenerator reportGenerator;
	
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
		return reportGenerator.getBytes(getParametersAporte(aporte), "recibosAportes.jasper");
	}
	
	@POST
	@Path("liquidar")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] liquidarCuotas(RequestLiquidarCuota request){
		request.getDetalles().forEach(d->d.setEstado("PAGADA"));
		creditosRepository.liquidarCuotas(request.getDetalles());
		Cliente cliente = getCliente((long) request.getIdcliente());
		return reportGenerator.getBytes(getParametrosAbonos(cliente,request), "recibosAbonosCreditos.jasper");
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
}
