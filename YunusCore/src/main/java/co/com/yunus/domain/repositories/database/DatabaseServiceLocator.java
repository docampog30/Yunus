package co.com.yunus.domain.repositories.database;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseServiceLocator {

	private static EntityManagerFactory emf;
	
	static{
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put( "javax.persistence.sharedCache.mode", "NONE");
		try{
			emf = Persistence.createEntityManagerFactory("ReservasUnit",properties);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	  private EntityManager em = emf.createEntityManager();

	  @Produces
	  public EntityManager em() {
	    return em;
	  }

	  public void dispose(@Disposes EntityManager em) {
	    em.close();
	  }
}
