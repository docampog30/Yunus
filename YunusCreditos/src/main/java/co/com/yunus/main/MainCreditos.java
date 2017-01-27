package co.com.yunus.main;

import java.util.Calendar;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

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
import co.com.yunus.application.rest.SimulacionServices;
import co.com.yunus.application.rest.VinculacionServices;
import co.com.yunus.config.AppExceptionMapper;
import co.com.yunus.config.CORSFilter;
import co.com.yunus.infrastructure.timer.TimerVencimientoCuotas;

public class MainCreditos {
	
	@Inject
	private TimerVencimientoCuotas timerVencimiento;
	
    private void initServer() {
	try {
		
		initTimer();
	    final ResourceConfig resourceConfig = new ResourceConfig();
	    resourceConfig.register(ClientesServices.class);
	    resourceConfig.register(MaestrosServices.class);
	    resourceConfig.register(VinculacionServices.class);
	    resourceConfig.register(SimulacionServices.class);
	    resourceConfig.register(CreditosServices.class);
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
        
        System.out.println("Server Iniciado !!");

	    server.setHandler(context);
	    server.start();
	    server.join();
	    
	    
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

	private void initTimer() {
	    Calendar with = Calendar.getInstance();
		int hour = with.get(Calendar.HOUR_OF_DAY);
	    int intDelayInHour = getHoursUntilTarget(22);
	   
	    System.out.println("Current Hour: " + hour);
	    System.out.println("Comuted Delay for next 1 am: " + intDelayInHour);
	    
	    
	    timerVencimiento.schedule(intDelayInHour);
	}
	
	private int getHoursUntilTarget(int targetHour) {
	    Calendar calendar = Calendar.getInstance();
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    return hour < targetHour ? targetHour - hour : targetHour - hour + 24;
	}

}
