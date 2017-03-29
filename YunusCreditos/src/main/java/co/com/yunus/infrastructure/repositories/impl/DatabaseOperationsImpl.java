package co.com.yunus.infrastructure.repositories.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.yunus.domain.repositories.operations.IRepositoryOperations;
import co.com.yunus.infrastructure.util.DatabaseServiceLocator;

public class DatabaseOperationsImpl implements IRepositoryOperations {

	@Inject
	private EntityManager entityManager;
	
	public <T> List<T> listar(String namedQueryName,Map<String, Object> parametros, Class<T> clazz) {
		TypedQuery<T> query = entityManager.createNamedQuery(namedQueryName,clazz);
		if(parametros!= null){
			for (Map.Entry<String, Object> entry : parametros.entrySet()) {
				query.setParameter((String) entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}
	
	public <T> void save(T object) {
		EntityTransaction tx = null;
		try {
		    tx = entityManager.getTransaction();
		    tx.begin();
		    entityManager.persist(object);
		    tx.commit();
		}
		catch (RuntimeException e) {
		    if (tx != null && tx.isActive()) 
		    	tx.rollback();
		    	throw e;
		}
		finally {
			entityManager.clear();
			entityManager.close();
			
		}
	}
	
	public <T> void update(T object) {
		EntityTransaction tx = null;
		try {
		    tx = entityManager.getTransaction();
		    tx.begin();
		    entityManager.merge(object);
		    tx.commit();
		}
		catch (RuntimeException e) {
		    if (tx != null && tx.isActive()) 
		    	tx.rollback();
		    	throw e;
		}
		finally {
			entityManager.clear();
			entityManager.close();
		}
	}

	public <T> void delete(Class<T> clazz,Object pk) {
		try {
			Object find = entityManager.find(clazz, pk);
			entityManager.getTransaction().begin();
			entityManager.remove(find);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}

	@Override
	public <T> void save(List<T> object) {
		EntityTransaction tx = null;
		try {
		    tx = entityManager.getTransaction();
		    tx.begin();
		    object.forEach(o->entityManager.persist(o));
		    tx.commit();
		}
		catch (RuntimeException e) {
		    if (tx != null && tx.isActive()) 
		    	tx.rollback();
		    	throw e;
		}
		finally {
			entityManager.clear();
			entityManager.close();
			
		}
	}

	@Override
	public void executeSQL(String sql,Map<String, Object> parametros) {
		
		EntityTransaction tx = null;
		if(!entityManager.isOpen()){
			entityManager = DatabaseServiceLocator.emf.createEntityManager();
		}
		
		try {
			tx = entityManager.getTransaction();
			Query q = entityManager.createQuery(sql);
		    tx.begin();
		    for (Map.Entry<String, Object> entry : parametros.entrySet()) {
				q.setParameter((String) entry.getKey(), entry.getValue());
			}
			q.executeUpdate();
		    tx.commit();
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		    if (tx != null && tx.isActive()) 
		    	tx.rollback();
		    	throw e;
		}
		finally {
			entityManager.clear();
			entityManager.close();
		}
		
	}

	@Override
	public <T> void update(List<T> object) {
		EntityTransaction tx = null;
		try {
		    tx = entityManager.getTransaction();
		    tx.begin();
		    object.forEach(o->entityManager.merge(o));
		    tx.commit();
		}
		catch (RuntimeException e) {
		    if (tx != null && tx.isActive()) 
		    	tx.rollback();
		    	throw e;
		}
		finally {
			entityManager.clear();
			entityManager.close();
			
		}
		
	}
}
