package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Maestro;
import co.com.yunus.domain.repositories.IMaestroRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("maestros")
public class MaestrosServices {

	@Inject
	//@Named("maestrosMock")
	@Named("MaestroRepositoryImpl")
	private IMaestroRepository maestroRepository;
	
	@Inject
	//@Named("mock")
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Path("{idparent}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Maestro> getMaestros(@PathParam("idparent") Long idParent){
		return maestroRepository.buscarMaestrosPorParent(idParent);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarMaestro(Maestro maes){
		transactionalRepository.update(maes);
	}
	
}
