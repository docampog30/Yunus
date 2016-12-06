package co.com.yunus.application.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidos;
	private String sexo;
	private String tipdoc;
	private String documento;
	private String expedido;
	private Date feexpedicion;
	private Long estadocivil;
	private Long niveleducativo;
	private Long tipovivienda;
	private Long zonavivienda;
	private Long personascargo;
	private Date fenacimiento;
	private String lugarnacimiento;
	private String direccion;
	private String dirresidencia;
	private String email;
	private String celular;
	private String telefono;
	private Long ocupacion;
	
	public Long getId() {
		return id;
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTipdoc() {
		return tipdoc;
	}
	public void setTipdoc(String tipodoc) {
		this.tipdoc = tipodoc;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getExpedido() {
		return expedido;
	}
	public void setExpedido(String expedido) {
		this.expedido = expedido;
	}
	public Date getFeexpedicion() {
		return feexpedicion;
	}
	public void setFeexpedicion(Date feexpedicion) {
		this.feexpedicion = feexpedicion;
	}
	public Long getEstadocivil() {
		return estadocivil;
	}
	public void setEstadocivil(Long estadocivil) {
		this.estadocivil = estadocivil;
	}
	public Long getNiveleducativo() {
		return niveleducativo;
	}
	public void setNiveleducativo(Long niveleducativo) {
		this.niveleducativo = niveleducativo;
	}
	public Long getTipovivienda() {
		return tipovivienda;
	}
	public void setTipovivienda(Long tipovivienda) {
		this.tipovivienda = tipovivienda;
	}
	public Long getZonavivienda() {
		return zonavivienda;
	}
	public void setZonavivienda(Long zonavivienda) {
		this.zonavivienda = zonavivienda;
	}
	public Long getPersonascargo() {
		return personascargo;
	}
	public void setPersonascargo(Long personascargo) {
		this.personascargo = personascargo;
	}
	public Date getFenacimiento() {
		return fenacimiento;
	}
	public void setFenacimiento(Date fenacimiento) {
		this.fenacimiento = fenacimiento;
	}
	public String getLugarnacimiento() {
		return lugarnacimiento;
	}
	public void setLugarnacimiento(String lugarnacimiento) {
		this.lugarnacimiento = lugarnacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDirresidencia() {
		return dirresidencia;
	}
	public void setDirresidencia(String dirresidencia) {
		this.dirresidencia = dirresidencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Long getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(Long ocupacion) {
		this.ocupacion = ocupacion;
	}
}
