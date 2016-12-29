package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.domain.repositories.ICreditosRepository;

@Path("creditos")
public class CreditosServices {

	@Inject
	ICreditosRepository creditosRepository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarMaestro(List<Credito> creditos){
		creditosRepository.guardarCreditoCliente(creditos);
	}
}
