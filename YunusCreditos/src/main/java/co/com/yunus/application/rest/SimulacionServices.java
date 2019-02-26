package co.com.yunus.application.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;
import co.com.yunus.application.dto.RequestSimulador;
import co.com.yunus.domain.services.SimuladorBussinesService;
import co.com.yunus.infrastructure.reports.ReportGenerator;

@Path("simulador")
public class SimulacionServices {
	
	@Inject
	private SimuladorBussinesService simuladorBussinesService;

	@Inject
	private ReportGenerator reportGenerator;
	
	@POST
	public List<Detalle> generarSimulacion(RequestSimulador request){
		return simuladorBussinesService.generarSimulacion(request);
	}
	
	@POST
	@Path("imprimir")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] getReport(List<Detalle> detalles){
		HashMap<String, Object> parametros = new HashMap<>();
		Credito cre = new Credito();
		cre.setFecha(new Date());
		parametros.put("credito", cre);
		return reportGenerator.getBytes(parametros, "amortizacionCreditos.jasper",detalles);
	}
	

}
