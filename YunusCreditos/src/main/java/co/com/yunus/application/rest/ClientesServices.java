package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("cliente")
public class ClientesServices {

	@Inject
	private ITransactionalRepository repository;
	
	@Inject
	private IClientesRepository clientesRepository;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Cliente cliente){
		repository.save(cliente);
	}
	
	@GET
	@Path("{documento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> guardar(@PathParam("documento") String documento){
		return clientesRepository.getClientByDocument(documento);
	}
	
}
