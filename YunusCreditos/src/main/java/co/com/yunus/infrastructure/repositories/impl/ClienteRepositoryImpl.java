package co.com.yunus.infrastructure.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

public class ClienteRepositoryImpl implements IClientesRepository {

	@Inject
	private IRepositoryOperations databaseOperations;
	public List<Cliente> getClientByDocument(String document) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("documento", document);
		return databaseOperations.listar(Cliente.BUSCAR_POR_DOCUMENTO, parametros, Cliente.class);
	}

}
