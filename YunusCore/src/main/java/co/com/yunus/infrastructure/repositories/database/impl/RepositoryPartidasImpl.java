package co.com.yunus.infrastructure.repositories.database.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;
import co.com.yunus.domain.repositories.IRepositoryPartidas;

public class RepositoryPartidasImpl implements IRepositoryPartidas {
	
	@Inject
	private DatabaseOperationsImpl operations;


	public List<Partida> findPartidasByP1(String documento) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cedula", "%"+documento+"%");
		return operations.listar("findByP1", parametros, Partida.class);
	}

	public List<Partida> findPartidasByP2(String documento) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cedula", "%"+documento+"%");
		return operations.listar("findByP2", parametros, Partida.class);
	}

	public List<Partida> findByNumeroAndTipo(String numero, TipoSacramento tipo) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numero", "%"+numero+"%");
		parametros.put("tipo", tipo);
		return operations.listar("findByNumeroAndTipo", parametros, Partida.class);
	}

	public Partida findOne(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return operations.listar("findById", parametros, Partida.class).get(0);
	}

}
