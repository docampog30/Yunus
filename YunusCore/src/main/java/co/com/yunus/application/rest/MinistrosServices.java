package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Persona;
import co.com.yunus.domain.repositories.IRepositoryPersonas;
import co.com.yunus.domain.repositories.ITransactionalRepository;

@Path("ministros")
public class MinistrosServices {
	
	@Inject
	private IRepositoryPersonas personasRepository;
	
	@Inject
	private ITransactionalRepository transactionalRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> getMinistros(){
		return personasRepository.findByTipoNotNull();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("activos")
	public List<Persona> getMinistrosActivos(){
		return personasRepository.findByTipoNotNullAndEstado(true);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Persona save(Persona ministro){
		transactionalRepository.save(ministro);
		return ministro;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Persona ministro){
		Persona persona = personasRepository.findOne(ministro.getId());
		persona.setNombres(ministro.getNombres());
		persona.setApellidos(ministro.getApellidos());
		persona.setTipo(ministro.getTipo());
		persona.setEstado(ministro.getEstado());
		transactionalRepository.update(persona);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Long id){
		Persona persona = personasRepository.findOne(id);
		transactionalRepository.delete(persona);
	}
}
