package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import co.com.yunus.application.dto.Detalle;
import co.com.yunus.application.dto.RequestSimulador;
import co.com.yunus.domain.services.SimuladorBussinesService;

@Path("simulador")
public class SimulacionServices {
	
	@Inject
	private SimuladorBussinesService simuladorBussinesService;

	@POST
	public List<Detalle> generarSimulacion(RequestSimulador request){
		return simuladorBussinesService.generarSimulacion(request);
	}
}
