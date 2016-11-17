package co.com.yunus.application.dto;

import java.util.Date;

public class PartidasFilter {
	
	private String documento;
	private Date fechaIni;
	private Date fechaFin;
	private String tipoSacramento;
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getTipoSacramento() {
		return tipoSacramento;
	}
	public void setTipoSacramento(String tipoSacramento) {
		this.tipoSacramento = tipoSacramento;
	}
}
