package co.com.yunus.application.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the confirmacion database table.
 * 
 */
@Entity
@Table(name="CONFIRMACION")
public class Confirmacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean curso;

	private boolean datoslaborales;

	private boolean direccion;

	private boolean estado;

	private boolean informacionf;

	private String otro;

	private boolean referenciaf;

	private boolean referenciap;
	
	private boolean referenciac;

	private boolean telefono;

	@ManyToOne
	@JoinColumn(name="IDVINCULACION")
	private Vinculacion vinculacion;

	public Confirmacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getCurso() {
		return this.curso;
	}

	public void setCurso(boolean curso) {
		this.curso = curso;
	}

	public boolean getDatoslaborales() {
		return this.datoslaborales;
	}

	public void setDatoslaborales(boolean datoslaborales) {
		this.datoslaborales = datoslaborales;
	}

	public boolean getDireccion() {
		return this.direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	public boolean getEstado() {
		return this.estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean getInformacionf() {
		return this.informacionf;
	}

	public void setInformacionf(boolean informacionf) {
		this.informacionf = informacionf;
	}

	public String getOtro() {
		return this.otro;
	}

	public void setOtro(String otro) {
		this.otro = otro;
	}

	public boolean getReferenciaf() {
		return this.referenciaf;
	}

	public void setReferenciaf(boolean referenciaf) {
		this.referenciaf = referenciaf;
	}

	public boolean getReferenciap() {
		return this.referenciap;
	}

	public void setReferenciap(boolean referenciap) {
		this.referenciap = referenciap;
	}

	public boolean getTelefono() {
		return this.telefono;
	}

	public void setTelefono(boolean telefono) {
		this.telefono = telefono;
	}

	public void setVinculacion(Vinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}

	public boolean isReferenciac() {
		return referenciac;
	}

	public void setReferenciac(boolean referenciac) {
		this.referenciac = referenciac;
	}

}