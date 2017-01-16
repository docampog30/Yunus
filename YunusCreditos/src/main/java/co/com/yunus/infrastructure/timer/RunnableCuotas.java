package co.com.yunus.infrastructure.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.domain.repositories.ITransactionalRepository;

public class RunnableCuotas implements Runnable {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	@Override
	public void run() {
		System.err.println("Runnable ::::::::");
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("today", new Date());
		transactionalRepository.executeSQL("UPDATE DETALLE s SET s.ESTADO = 'VENCIDA' WHERE s.FECHA < :today AND s.ESTADO = 'VIGENTE'",parametros);
	}

}
