package co.com.yunus.application.dto;

import java.util.List;

public class RequestLiquidarCuota {
	private List<Detalle> detalles;
	private long idcliente;
	private long idcredito;
	public List<Detalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	public long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}
	public long getIdcredito() {
		return idcredito;
	}
	public void setIdcredito(long idcredito) {
		this.idcredito = idcredito;
	}
}
