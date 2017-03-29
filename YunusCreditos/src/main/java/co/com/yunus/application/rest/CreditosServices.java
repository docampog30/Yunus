package co.com.yunus.application.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import co.com.yunus.application.dto.Aporte;
import co.com.yunus.application.dto.Cliente;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;
import co.com.yunus.application.dto.InformeAportes;
import co.com.yunus.application.dto.InformeIngresos;
import co.com.yunus.application.dto.InformeMora;
import co.com.yunus.application.dto.RequestCredito;
import co.com.yunus.application.dto.RequestLiquidarCuota;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.infrastructure.excel.ExcelFileDescriptor;
import co.com.yunus.infrastructure.excel.ExcelGenerator;
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
	
	@Inject
	@Named("excelGeneric")
	private ExcelGenerator<Aporte> excelAporteGenerator;
	
	@Inject
	@Named("excelGeneric")
	private ExcelGenerator<Detalle> excelGenerator;

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("aportes/findByCliente/{cedula}")
	public List<Aporte> aportesFindByCliente(@PathParam("cedula")String cedula){
		List<Aporte> aportes = creditosRepository.findAporteByCliente(cedula);
		aportes.forEach(ap->ap.setCliente(null));
		return aportes;
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
	
	@GET
	@Path("reporte/{cddocumento}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaExcelDetalles(@PathParam("cddocumento") String documento) throws IOException{
		
		List<Detalle> detalles = creditosRepository.findByCliente(documento).stream()
				.flatMap(d-> d.getDetalles().stream())
					.filter(d->"PAGADA".equals(d.getEstado()))
						.collect(Collectors.toList());
		
		byte[] generate = excelGenerator.generate(detalles, new ExcelFileDescriptor(InformeIngresos.values()), "Ingresos creditos "+documento);
		java.nio.file.Path path = Files.createTempFile("sample-file", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
	
		response.header("Content-Disposition","attachment; filename=sample-file.xlsx");
		return response.build();
	}
	
	@GET
	@Path("aportes/reporte/{cddocumento}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaExcelAportesBydocumento(@PathParam("cddocumento") String documento) throws IOException{
		
		List<Aporte> aportes = creditosRepository.findAporteByCliente(documento);
		
		byte[] generate = excelAporteGenerator.generate(aportes, new ExcelFileDescriptor(InformeAportes.values()), "Ingresos Aportes "+documento);
		java.nio.file.Path path = Files.createTempFile("sample-file", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
	
		response.header("Content-Disposition","attachment; filename=sample-file.xlsx");
		return response.build();
	}
	
	@GET
	@Path("aportes/reporte/{feini}/{fefin}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaExcelAportes(@PathParam("feini")Long feIni,@PathParam("fefin")Long feFin) throws IOException{
		
		List<Aporte> aportes = creditosRepository.findAportesBetweenDates(new Date(feIni), new Date(feFin));
		
		byte[] generate = excelAporteGenerator.generate(aportes, new ExcelFileDescriptor(InformeAportes.values()), "Ingresos total aportes ");
		java.nio.file.Path path = Files.createTempFile("sample-file", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
	
		response.header("Content-Disposition","attachment; filename=sample-file.xlsx");
		return response.build();
	}
	
	@GET
	@Path("morosos/{feini}/{fefin}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaExcelMorosos(@PathParam("feini")Long feIni,@PathParam("fefin")Long feFin) throws IOException{
		
		List<Detalle> aportes = creditosRepository.findDetallesMora(new Date(feIni), new Date(feFin));
		
		byte[] generate = excelGenerator.generate(aportes, new ExcelFileDescriptor(InformeMora.values()), "Reporte Morosos");
		java.nio.file.Path path = Files.createTempFile("morosos", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
		response.header("Content-Disposition","attachment; filename=morosos.xlsx");
		return response.build();
	}
	
	@GET
	@Path("morosos/{documento}")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generaExcelMorosos(@PathParam("documento") String documento) throws IOException{
		
		List<Detalle> aportes = creditosRepository.findDetallesMoraByCliente(documento);
		
		byte[] generate = excelGenerator.generate(aportes, new ExcelFileDescriptor(InformeMora.values()), "Reporte Morosos");
		java.nio.file.Path path = Files.createTempFile("ClienteMoroso", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
		response.header("Content-Disposition","attachment; filename=morosos.xlsx");
		return response.build();
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
										d.setValorpagado(d.getFecha().after(new Date()) ? d.getAmortizacion():d.getCuota());
									});
		creditosRepository.liquidarCuotas(request.getDetalles());
		Cliente cliente = getCliente((long) request.getIdcliente());
		
		Map<String, Object> parametrosAbonos = getParametrosAbonos(cliente,request);
		
		String periodos = request.getDetalles().stream().map(x -> ""+x.getPeriodo()).collect(Collectors.joining(", ", "[", "]"));
		
		sender.enviarMailAbono(cliente.getEmail(),String.format("Abono al credito # %s COOPECEJA, %s %s", request.getIdcredito(),cliente.getNombres(),cliente.getApellidos()),getMailContent(parametrosAbonos,periodos));
		
		
		return reportGenerator.getBytes(parametrosAbonos, "recibosAbonosCreditos.jasper");
	}
	
	@GET
	@Path("aporte/sum/{feini}/{fefin}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BigDecimal aporteGetSum(@PathParam("feini")Long feIni,@PathParam("fefin")Long feFin){
		return creditosRepository.findAportesSumBetweenDates(new Date(feIni),new Date(feFin));
	}
	
	@DELETE
	@Path("aporte/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteAporte(@PathParam("id")Long id){
		creditosRepository.deleteAporte(id);
	}
	
	@GET
	@Path("sum/{feini}/{fefin}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BigDecimal getSum(@PathParam("feini")Long feIni,@PathParam("fefin")Long feFin){
		return creditosRepository.findSumBetweenDates(new Date(feIni),new Date(feFin));
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
	
	private String getMailContent(Map<String, Object> parametrosAbonos,String periodos) {
		String content = "Señor(a) \n%s %s\n\nUsted acaba de generar abono al credito # %s de %s cuota(s) para el(los) periodo(s) %s , por valor de: %s.\nPara COOPECEJA es un gusto poderle prestar nuestros mejores servicios y proporcionales esta información como evidencia de su pago.\n\nAgradecemos su atención.\n\nCOOPECEJA";
		
		Cliente cliente =  (Cliente) parametrosAbonos.get("cliente");
		return String.format(content,cliente.getNombres(),cliente.getApellidos(),parametrosAbonos.get("credito"),parametrosAbonos.get("cuotas"),periodos,parametrosAbonos.get("valor"));
	}

	private Map<String, Object> getParametrosAbonos(Cliente cliente, RequestLiquidarCuota request) {
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("credito", request.getIdcredito());
		parameters.put("numaporte", request.getDetalles().get(0).getId());
		parameters.put("cliente", cliente);
		parameters.put("cuotas", request.getDetalles().size());
		parameters.put("saldo", request.getDetalles().stream()
                .max((p1, p2) -> Integer.compare( p1.getPeriodo(), p2.getPeriodo()))
                .get().getSaldofinal());
		parameters.put("valor", request.getDetalles()
								.stream()
								 .map(Detalle::getValorpagado)
							        .reduce(BigDecimal.ZERO, BigDecimal::add));
		return parameters;
	}
}
