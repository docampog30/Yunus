package co.com.yunus.infrastructure.repositories.impl;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;

public class CreditosRepositoryImpl implements ICreditosRepository {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;

	@Override
	public void guardarCreditoCliente(Credito credito) {
		transactionalRepository.save(credito);
	}

}
