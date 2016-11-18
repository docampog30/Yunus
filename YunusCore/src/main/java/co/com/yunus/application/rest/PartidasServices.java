package co.com.yunus.application.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;
import co.com.yunus.domain.repositories.IRepositoryPartidas;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.exception.AppException;

@Path("partidas")
public class PartidasServices {
	
	@Inject
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private IRepositoryPartidas partidasRepository;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("matrimonio")
	public void crearMatrimonio(Partida partida){
		partida.setTipo(TipoSacramento.MATRIMONIO);
		save(partida);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("bautizo")
	public void crearBautizmo(Partida partida){
		partida.setTipo(TipoSacramento.BAUTIZO);
		save(partida);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("confirmacion")
	public void crearConfirmacion(Partida partida){
		partida.setTipo(TipoSacramento.CONFIRMACION);
		save(partida);
	}
	
	private void save(Partida partida) {
		List<Partida> partidas = partidasRepository.findByNumeroAndTipo(partida.getNumero(),partida.getTipo());
		if(partidas.isEmpty()){
			transactionalRepository.save(partida);
		}else{
			throw new AppException("Número repetido");
		}
	}
}
