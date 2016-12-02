package co.com.yunus.application.dto;

import javax.persistence.Id;

public class Maestro {

	private Long id;
	private String descripcion;
	private String codigo;
	private Maestro parent;
	private Long idParent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Maestro getParent() {
		return parent;
	}
	public void setParent(Maestro parent) {
		this.parent = parent;
	}
	public Long getIdParent() {
		return idParent;
	}
	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}
}
