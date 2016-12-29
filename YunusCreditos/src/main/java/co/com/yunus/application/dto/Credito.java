package co.com.yunus.application.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="CREDITO")
@Entity
public class Credito {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long 			id;
	private Integer 		periodo;
	private BigDecimal 		saldoInicial;
	private BigDecimal 		intereses;
	private BigDecimal		cuota;
	private BigDecimal		amortizacion;
	private BigDecimal 		saldoFinal;
	private Date			fecha;
	private Long 			idcliente;
	
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public BigDecimal getIntereses() {
		return intereses;
	}
	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}
	public BigDecimal getCuota() {
		return cuota;
	}
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	public BigDecimal getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(BigDecimal amortizacion) {
		this.amortizacion = amortizacion;
	}
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}
}
