package co.com.yunus.infrastructure.repositories.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Override
	public BigDecimal findAportesSumBetweenDates(Date date, Date date2) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("startDate", date);
		parametros.put("endDate", date2);
		List<Aporte> aportes = databaseOperations.listar(Aporte.FINDBYBETWEENDATE, parametros, Aporte.class);
		return aportes.stream().map(Aporte::getValor)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public List<Aporte> findAporteByCliente(String cedula) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("documento", cedula);
		return databaseOperations.listar(Aporte.FINDBYCLIENTE, parametros, Aporte.class);
	}

	@Override
	public List<Aporte> findAportesBetweenDates(Date date, Date date2) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("startDate", date);
		parametros.put("endDate", date2);
		return databaseOperations.listar(Aporte.FINDBYBETWEENDATE, parametros, Aporte.class);
	}

	@Override
	public List<Detalle> findDetallesMora(Date date, Date date2) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("startDate", date);
		parametros.put("endDate", date2);
		return databaseOperations.listar(Detalle.FINDMOROSOSBYBETWEENDATE, parametros, Detalle.class);
	}

	@Override
	public void deleteAporte(Long id) {
		transactionalRepository.delete(Aporte.class,id);
	}

	@Override
	public List<Detalle> findDetallesMoraByCliente(String documento) {
		Map<String,Object> parametros = new HashMap<>();
		parametros.put("documento", documento);
		List<Detalle> list = databaseOperations.listar(Credito.FIND_BY_DOCUMENTO_CLIENTE, parametros, Credito.class)
			.parallelStream()
				.flatMap(c-> c.getDetalles().stream())
					.filter(d->"VENCIDA".equals(d.getEstado()))
						.collect(Collectors.toList());
		return list;
	}
}
