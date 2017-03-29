package co.com.yunus.infrastructure.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.domain.repositories.ITransactionalRepository;

public class RunnableCuotas {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	public void run() {
		System.err.println("Runnable ::::::::");
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("today", new Date());
		transactionalRepository.executeSQL("UPDATE Detalle s SET s.estado = 'VENCIDA' WHERE s.fecha < :today AND s.estado = 'PENDIENTE'",parametros);
	}

}
