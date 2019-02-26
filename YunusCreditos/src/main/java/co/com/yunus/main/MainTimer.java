package co.com.yunus.main;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import co.com.yunus.infrastructure.timer.TimerVencimientoCuotas;

public class MainTimer {
	
	@Inject
	private TimerVencimientoCuotas timerVencimiento;
	
	public void start(@Observes final ContainerInitialized event) {
		try {
		    initTimer();
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
	 private void initTimer() {
		    timerVencimiento.schedule(0);
	 }
}
