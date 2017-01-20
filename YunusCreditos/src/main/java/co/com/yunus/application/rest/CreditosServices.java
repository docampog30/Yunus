package co.com.yunus.application.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Aporte;
import co.com.yunus.application.dto.Cliente;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;
import co.com.yunus.application.dto.RequestCredito;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.infrastructure.reports.ReportGenerator;

@Path("creditos")
public class CreditosServices {

	@Inject
	private ICreditosRepository creditosRepository;
	
	@Inject
	@Named("ClienteRepositoryImpl")
	private IClientesRepository clientesRepository;
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	ReportGenerator reportGenerator;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Credito guardarCredito(RequestCredito request){
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("findByCliente/{cedula}")
	public List<Credito> findByCliente(@PathParam("cedula")String cedula){
		List<Credito> credito = creditosRepository.findByCliente(cedula);
		credito.forEach(d-> d.getDetalles().forEach(c-> c.setCredito(null)));
		return credito;
	}
	
	@POST
	@Path("aporte")
	@Consumes(MediaType.APPLICATION_JSON)
	public byte[] liquidarAporte(RequestCredito request){
		Aporte aporte = new Aporte();
		aporte.setFecha(new Date());
		aporte.setIdcliente(request.getIdcliente());
		aporte.setValor(request.getValor());
		aporte.setTipo(request.getTipaporte());
		creditosRepository.guardarAporte(aporte);
		Cliente cliente = getCliente((long) request.getIdcliente());
		aporte.setCliente(cliente);
		return reportGenerator.getBytes(getParameters(aporte), "recibosAportes.jasper");
	}
	
	private Cliente getCliente(Long id) {
		return clientesRepository.getClientByID(id).get(0);
	}

	private Map<String, Object> getParameters(Aporte aporte) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("aporte", aporte);
		return parameters;
	}
}
