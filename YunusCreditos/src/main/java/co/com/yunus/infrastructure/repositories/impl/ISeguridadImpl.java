package co.com.yunus.infrastructure.repositories.impl;

import java.util.HashMap;

import javax.inject.Inject;

import co.com.yunus.application.dto.LoginRequest;
import co.com.yunus.application.dto.Usuario;
import co.com.yunus.domain.repositories.ISeguridadRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

public class ISeguridadImpl implements ISeguridadRepository {
	
	@Inject
	private IRepositoryOperations repositoryOperations;

	@Override
	public boolean checkLogin(LoginRequest request) {
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("user", request.getUser());
		parametros.put("password", request.getPassword());
		return !repositoryOperations.listar(Usuario.FIND_BY_USER_PASSWORD, parametros, Usuario.class).isEmpty();
	}

}
