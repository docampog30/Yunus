package co.com.yunus.application.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BENEFICIARIO")
public class Beneficiario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String documento;
	private String nombre;
	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;
	private String parentesco;
	private String telefono;
	private String  designacion;
	@ManyToOne
	@JoinColumn(name="IDVINCULACION",nullable=false)
	private Vinculacion vinculacion;
	
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
	public void setVinculacion(Vinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}
}
