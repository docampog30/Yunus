package co.com.yunus.infrastructure.repositories.database.impl;

import javax.inject.Named;

import co.com.yunus.domain.repositories.ITransactionalRepository;

@Named("mock")
public class TransactionalMockImpl implements ITransactionalRepository {

	@Override
	public <T> void save(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(T object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void delete(T object) {
		// TODO Auto-generated method stub
		
	}

}
