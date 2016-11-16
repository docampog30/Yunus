package co.com.yunus.infrastructure.repositories.database.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.com.yunus.domain.repositories.database.IDatabaseOperations;

public class DatabaseOperationsImpl implements IDatabaseOperations {

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
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
		
	}
	
	public <T> void update(T object) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
		
	}

	public <T> void delete(T object) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}
}
