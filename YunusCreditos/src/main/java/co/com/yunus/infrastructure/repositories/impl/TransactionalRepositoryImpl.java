package co.com.yunus.infrastructure.repositories.impl;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

@Named("TransactionalRepositoryImpl")
@Default
public class TransactionalRepositoryImpl implements ITransactionalRepository {

	@Inject
	private IRepositoryOperations dataBaseOperations;
	
	public <T> void save(T object) {
		dataBaseOperations.save(object);
	}

	public <T> void update(T object) {
		dataBaseOperations.update(object);
	}

	public <T> void delete(T object) {
		dataBaseOperations.delete(object);
	}
}
