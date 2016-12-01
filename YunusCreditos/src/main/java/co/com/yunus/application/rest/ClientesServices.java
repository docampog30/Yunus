package co.com.yunus.application.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("cliente")
public class ClientesServices {

	@Inject
	private ITransactionalRepository repository;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Cliente cliente){
		repository.save(cliente);
	}
	
}
