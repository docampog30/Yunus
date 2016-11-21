package co.com.yunus.application.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Parroquia;
import co.com.yunus.domain.repositories.IRepositoryParroquias;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("parroquia")
public class ParroquiaServices {
	@Inject
	private IRepositoryParroquias parroquiasRepository;
	@Inject
	private ITransactionalRepository transactionalRepository;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Parroquia getConfig(){
		return parroquiasRepository.findAll().get(0);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateConfig(Parroquia parroquia){
		transactionalRepository.update(parroquia);
	}
}
