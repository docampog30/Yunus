package co.com.yunus.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class RequestCredito {

	private List<Detalle> detalles;
	private BigDecimal valor;
	private int plazo;
	private float interes;
	private int idcliente;
	private String tipaporte;
	public List<Detalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public float getInteres() {
		return interes;
	}
	public void setInteres(float interes) {
		this.interes = interes;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getTipaporte() {
		return tipaporte;
	}
	public void setTipaporte(String tipaporte) {
		this.tipaporte = tipaporte;
	}
	
	
}
