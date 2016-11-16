package co.com.yunus.application.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="PERSONAS")
@Entity
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String 		nombres;
	private String 		apellidos;
	private String 		cedula;
	private String 		tipo;
	private boolean	 	estado;
	private Date 		fNacimiento;
	@Column(name="NOMBRE_PADRE1")
	private String 		nombrePadre1;
	@Column(name="NOMBRE_PADRE2")
	private String 		nombrePadre2;
	@Column(name="AB_MATERNOS")
	private String 		abuelosm;
	@Column(name="AB_PATERNOS")
	private String 		abuelosp;
	@Column(name="LUGAR_NMTO")
	private String 		lugarNacimiento;
	private String 		sexo;
	
	public String getAbuelosm() {
		return abuelosm;
	}
	public void setAbuelosm(String abuelosm) {
		this.abuelosm = abuelosm;
	}
	public String getAbuelosp() {
		return abuelosp;
	}
	public void setAbuelosp(String abuelosp) {
		this.abuelosp = abuelosp;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Date getfNacimiento() {
		return fNacimiento;
	}
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public String getNombrePadre1() {
		return nombrePadre1;
	}
	public void setNombrePadre1(String nombrePadre1) {
		this.nombrePadre1 = nombrePadre1;
	}
	public String getNombrePadre2() {
		return nombrePadre2;
	}
	public void setNombrePadre2(String nombrePadre2) {
		this.nombrePadre2 = nombrePadre2;
	}
}
