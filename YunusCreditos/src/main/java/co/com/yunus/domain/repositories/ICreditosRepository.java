package co.com.yunus.domain.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.com.yunus.application.dto.Aporte;
import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.Detalle;

public interface ICreditosRepository {
	void guardarCreditoCliente(Credito credito);

	List<Credito> findByCliente(String cedula);

	void guardarAporte(Aporte aporte);

	void liquidarCuotas(List<Detalle> detalles);

	BigDecimal findSumBetweenDates(Date feIni, Date feFin);

	BigDecimal findAportesSumBetweenDates(Date date, Date date2);
	List<Aporte> findAportesBetweenDates(Date date, Date date2);
	List<Aporte> findAporteByCliente(String cedula);

	List<Detalle> findDetallesMora(Date date, Date date2);

	void deleteAporte(Long id);

	List<Detalle> findDetallesMoraByCliente(String documento);
}
