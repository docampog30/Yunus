package co.com.yunus.main;

import javax.enterprise.event.Observes;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.se.StartMain;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import co.com.yunus.application.rest.ClientesServices;
import co.com.yunus.application.rest.CreditosServices;
import co.com.yunus.application.rest.MaestrosServices;
import co.com.yunus.application.rest.SeguridadServices;
import co.com.yunus.application.rest.SimulacionServices;
import co.com.yunus.application.rest.VinculacionServices;
import co.com.yunus.config.AppExceptionMapper;
import co.com.yunus.config.CORSFilter;

public class MainCreditos {
	
    private void initServer() {
	try {
	    final ResourceConfig resourceConfig = new ResourceConfig();
	    resourceConfig.register(ClientesServices.class);
	    resourceConfig.register(MaestrosServices.class);
	    resourceConfig.register(VinculacionServices.class);
	    resourceConfig.register(SimulacionServices.class);
	    resourceConfig.register(CreditosServices.class);
	    resourceConfig.register(SeguridadServices.class);
	    resourceConfig.register(JacksonFeature.class);
	    resourceConfig.register(new CORSFilter());	    
	    resourceConfig.registerInstances(new AppExceptionMapper());
	    resourceConfig.registerInstances(new ResourceConfig()
	    .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
	    .property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true));
	    
	    PropertyConfigurator.configure(this.getClass().getClassLoader().getResourceAsStream("META-INF/log4j.properties"));
		BasicConfigurator.configure();
	    final ServletContainer servletContainer = new ServletContainer(resourceConfig);
	    final ServletHolder sh = new ServletHolder(servletContainer);
	    final Server server = new Server(8181);
	    
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/app");
        context.addServlet(sh, "/*");
        
	    server.setHandler(context);
	    server.start();
	    server.join();
	    System.out.println("Server Iniciado !!");
	    
	    
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public static void main(String[] args) {
    	StartMain.main(args);
    	
    }

    public void start(@Observes final ContainerInitialized event) {
		try {
		    initServer();
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
    }
}
