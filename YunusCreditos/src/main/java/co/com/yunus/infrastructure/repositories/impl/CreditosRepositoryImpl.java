package co.com.yunus.infrastructure.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

public class CreditosRepositoryImpl implements ICreditosRepository {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private IRepositoryOperations databaseOperations;

	@Override
	public void guardarCreditoCliente(Credito credito) {
		transactionalRepository.save(credito);
	}

	@Override
	public List<Credito> findByCliente(String cedula) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("documento", cedula);
		return databaseOperations.listar(Credito.FIND_BY_DOCUMENTO_CLIENTE, parametros, Credito.class);
	}

}
