package co.com.yunus.infrastructure.repositories.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public <T> void save(List<T> object) {
		dataBaseOperations.save(object);
		
	}

	@Override
	public void executeSQL(String sql,Map<String, Object> parametros) {
		dataBaseOperations.executeSQL(sql, parametros);
		
	}

	@Override
	public <T> void update(List<T> object) {
		dataBaseOperations.update(object);
		
	}
}
