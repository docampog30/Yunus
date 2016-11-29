package co.com.yunus.infrastructure.repositories.database.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import co.com.yunus.application.dto.Parroquia;
import co.com.yunus.domain.repositories.IRepositoryParroquias;
import co.com.yunus.domain.repositories.database.IDatabaseOperations;

public class RepositoryParroquiasImpl implements IRepositoryParroquias {

	@Inject
	private IDatabaseOperations databaseOperations;
	public List<Parroquia> findAll() {
		return databaseOperations.listar(Parroquia.FIND_ALL, new HashMap<String, Object>(), Parroquia.class);
	}
}