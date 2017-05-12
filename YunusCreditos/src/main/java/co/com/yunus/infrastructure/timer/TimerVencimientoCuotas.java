package co.com.yunus.infrastructure.timer;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class TimerVencimientoCuotas {
	
	@Inject
	private RunnableCuotas task;
	
	  public void schedule(long delay) {
		  ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	        Runnable pinger = new Runnable() {
	            public void run() {
	                System.out.println("PING! "+new Date());
	                task.run();
	            }
	        };
	        ses.scheduleAtFixedRate(pinger,delay,1,TimeUnit.HOURS);
	  }
	}