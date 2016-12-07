package co.com.yunus.infrastructure.repositories.database.impl;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.domain.repositories.database.IDatabaseOperations;

@Named("TransactionalRepositoryImpl")
public class TransactionalRepositoryImpl implements ITransactionalRepository {

	@Inject
	private IDatabaseOperations dataBaseOperations;
	
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
