package co.com.yunus.application.dto;

import java.math.BigDecimal;

public class RequestSimulador {

	private int meses;
	private BigDecimal valor;
	private BigDecimal interes;
	
	public int getMeses() {
		return meses;
	}
	public void setMeses(int meses) {
		this.meses = meses;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getInteres() {
		return interes;
	}
	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}
}
