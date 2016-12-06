package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Maestro;
import co.com.yunus.domain.repositories.IMaestroRepository;

@Path("maestros")
public class MaestrosServices {

	@Inject
	@Named("maestrosMock")
	private IMaestroRepository maestroRepository;
	
	@Path("{idparent}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Maestro> getMaestros(@PathParam("idparent") Long idParent){
		return maestroRepository.buscarMaestrosPorParent(idParent);
	}
	
}
