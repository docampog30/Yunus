package co.com.yunus.infrastructure.util;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseServiceLocator {

	public static EntityManagerFactory emf;
	
	static{
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put( "javax.persistence.sharedCache.mode", "NONE");
		try{
			emf = Persistence.createEntityManagerFactory("CreditosUnit",properties);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	  private EntityManager em = emf.createEntityManager();

	  @Produces
	  public EntityManager em() {
		if(!em.isOpen())
			em = emf.createEntityManager();
	    return em;
	  }

	  public void dispose(@Disposes EntityManager em) {
	    em.close();
	  }
}
