package co.com.yunus.infrastructure.repositories.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.application.dto.Aporte;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;
import co.com.yunus.domain.repositories.ICreditosRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

public class CreditosRepositoryImpl implements ICreditosRepository {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private IRepositoryOperations databaseOperations;

	@Override
	public void guardarCreditoCliente(Credito credito) {
		transactionalRepository.save(credito);
	}

	@Override
	public List<Credito> findByCliente(String cedula) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("documento", cedula);
		return databaseOperations.listar(Credito.FIND_BY_DOCUMENTO_CLIENTE, parametros, Credito.class);
	}

	@Override
	public void guardarAporte(Aporte aporte) {
		transactionalRepository.save(aporte);
	}

	@Override
	public void liquidarCuotas(List<Detalle> detalles) {
		transactionalRepository.update(detalles);
		
	}

	@Override
	public BigDecimal findSumBetweenDates(Date feIni, Date feFin) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("startDate", feIni);
		parametros.put("endDate", feFin);
		List<Detalle> detalles = databaseOperations.listar(Detalle.FINDBYBETWEENDATE, parametros, Detalle.class);
		return detalles.stream().map(Detalle::getCuota)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
