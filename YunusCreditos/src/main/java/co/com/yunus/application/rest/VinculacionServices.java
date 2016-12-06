package co.com.yunus.application.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Vinculacion;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("vinculacion")
public class VinculacionServices {
	
	@Inject
	@Named("mock")
	private ITransactionalRepository transactionalRepository;
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Vinculacion guardar(Vinculacion vinculacion){
		transactionalRepository.save(vinculacion);
		return vinculacion;
	}
}
