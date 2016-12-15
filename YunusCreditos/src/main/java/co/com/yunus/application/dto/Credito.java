package co.com.yunus.application.dto;

import java.math.BigDecimal;

public class Credito {
	private Integer 	periodo;
	private BigDecimal 		saldoInicial;
	private BigDecimal 		intereses;
	private BigDecimal		cuota;
	private BigDecimal		amortizacion;
	private BigDecimal 		saldoFinal;
	
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
}
