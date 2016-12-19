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

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.exception.AppException;

@Path("cliente")
public class ClientesServices {

	@Inject
	@Named("ClienteRepositoryImpl")
	private IClientesRepository clientesRepository;
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Cliente cliente) throws Exception{
		validarCedula(cliente.getDocumento());
		transactionalRepository.save(cliente);
	}
	
	private void validarCedula(String documento) {
		if(!clientesRepository.getClientByDocument(documento).isEmpty()){
			throw new AppException("Este cliente ya existe");
		}
	}

	@GET
	@Path("{documento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getCliente(@PathParam("documento") String documento){
		return clientesRepository.getClientByDocument(documento);
	}
	
}
