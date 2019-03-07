package co.com.yunus.application.rest;

import java.io.IOException;
import java.nio.file.Files;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.application.dto.InformeClientes;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.infrastructure.excel.ExcelFileDescriptor;
import co.com.yunus.infrastructure.excel.ExcelGenerator;

@Path("cliente")
public class ClientesServices {

	@Inject
	//@Named("clienteMock")
	@Named("ClienteRepositoryImpl")
	private IClientesRepository clientesRepository;
	
	@Inject
	//@Named("mock")
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	@Named("excelGeneric")
	private ExcelGenerator<Cliente> excelGenerator;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Cliente cliente){
		transactionalRepository.update(cliente);
	}
	
	@GET
	@Path("{documento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getCliente(@PathParam("documento") String documento){
		
		List<Cliente> clientByDocument = clientesRepository.getClientByDocument(documento);
		clientByDocument.stream()
				.forEach(c->c.getVinculaciones().stream().forEach(v->v.setCliente(null)));
		return clientByDocument;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientes() throws IOException{
		
		List<Cliente> clients = clientesRepository.getClients();
		byte[] generate = excelGenerator.generate(clients, new ExcelFileDescriptor(InformeClientes.values()), "Reporte Clientes");
		java.nio.file.Path path = Files.createTempFile("Clientes", ".xlsx");
		Files.write(path, generate);
		ResponseBuilder response = Response.ok(path.toFile());
		response.header("Content-Disposition","attachment; filename=clientes.xlsx");
		return response.build();
	}
	
}
