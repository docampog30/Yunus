package co.com.yunus.application.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.RequestCredito;
import co.com.yunus.domain.repositories.ICreditosRepository;

@Path("creditos")
public class CreditosServices {

	@Inject
	ICreditosRepository creditosRepository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Credito actualizarMaestro(RequestCredito request){
		Credito credito = new Credito();
		credito.setFecha(new Date());
		credito.setIdcliente(request.getIdcliente());
		credito.setInteres(request.getInteres());
		credito.setPlazo(request.getPlazo());
		credito.setValor(request.getValor().floatValue());
		credito.setDetalles(request.getDetalles());
		credito.getDetalles().forEach(d-> {
		   d.setEstado("PENDIENTE");
		   d.setCredito(credito);
		   });
		
		creditosRepository.guardarCreditoCliente(credito);
		credito.getDetalles().forEach(d-> d.setCredito(null));
		return credito;
	}
}
