package co.com.yunus.infrastructure.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerVencimientoCuotas {

	  public static void schedule(final Runnable r, long delay) {
		  ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	        Runnable pinger = new Runnable() {
	            public void run() {
	                System.out.println("PING!");
	            }
	        };
	        ses.scheduleAtFixedRate(pinger, delay, 24, TimeUnit.HOURS);
	  }
	}