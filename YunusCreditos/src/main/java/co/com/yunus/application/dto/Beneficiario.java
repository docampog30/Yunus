package co.com.yunus.application.dto;

import java.util.Date;

public class Beneficiario {
	private String documento;
	private String nombre;
	private Date fechanacimiento;
	private String parentesco;
	private String telefono;
	private String  designacion;
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getParentesco() {
		return parentesco;
	}
	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDesignacion() {
		return designacion;
	}
	public void setDesignacion(String designacion) {
		this.designacion = designacion;
	}
	
}
