package co.com.yunus.infrastructure.repositories.database.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.yunus.application.dto.Persona;
import co.com.yunus.domain.repositories.IRepositoryPersonas;
import co.com.yunus.domain.repositories.database.IDatabaseOperations;

public class RepositoryPersonasImpl implements IRepositoryPersonas {

	@Inject
	private IDatabaseOperations databaseOperations;
	
	public List<Persona> findByTipoNotNullAndEstado(boolean b) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("estado", true);
		return databaseOperations.listar(Persona.FIND_BY_TIPO_NOT_NULL_AND_ESTADO, parametros, Persona.class);
	}

	public List<Persona> findByTipoNotNull() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		return databaseOperations.listar(Persona.FIND_BY_TIPO_NOT_NULL, parametros, Persona.class);
	}

	public Persona findByTipo(String tipo) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tipo", tipo);
		return databaseOperations.listar(Persona.FIND_BY_TIPO, parametros, Persona.class).stream().findFirst().get();
	}
	public Persona findOne(Long id) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		return databaseOperations.listar(Persona.FIND_BY_TIPO, parametros, Persona.class).stream().findFirst().get();
	}
}
