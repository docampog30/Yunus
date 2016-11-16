package co.com.yunus.domain.repositories.database;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseServiceLocator {
	
	private static EntityManagerFactory emf;
	
	static{
		try{
			emf = Persistence.createEntityManagerFactory("ReservasUnit");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Produces
	public EntityManager getEntityManager(){
		return  emf.createEntityManager();
	}
}
