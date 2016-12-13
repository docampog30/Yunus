package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;
import co.com.yunus.domain.repositories.IRepositoryPartidas;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import net.sf.jasperreports.engine.JRException;

@Path("partidas")
public class PartidasServices {
	
	@Inject
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private ReportesServices reportesServices;
	
	@Inject
	private IRepositoryPartidas partidasRepository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("matrimonio")
	public byte[] crearMatrimonio(Partida partida) throws JRException, Exception{
		partida.setTipo(TipoSacramento.MATRIMONIO);
		save(partida);
		return reportesServices.generarPartidaMatrimonio(partida.getId());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("bautizo")
	public byte[] crearBautizmo(Partida partida) throws JRException, Exception{
		partida.setTipo(TipoSacramento.BAUTIZO);
		save(partida);
		return reportesServices.generarPartidaBautizo(partida.getId());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("confirmacion")
	public byte[]  crearConfirmacion(Partida partida) throws JRException, Exception{
		partida.setTipo(TipoSacramento.CONFIRMACION);
		save(partida);
		return reportesServices.generarPartidaConfirmacion(partida.getId());
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Partida getById(Long id){
		return partidasRepository.findOne(id);
	}
	
	private void save(Partida partida) {
		transactionalRepository.save(partida);
	}
}
