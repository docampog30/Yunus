package co.com.yunus.application.dto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="PARROQUIA")
@Cacheable(false)
@Cache(usage=CacheConcurrencyStrategy.NONE)
@NamedQueries({
		@NamedQuery(name=Parroquia.FIND_ALL,query="SELECT P FROM Parroquia P")	
})
public class Parroquia {
	
	public static final String FIND_ALL = "FIND_ALL";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String diocesis;
	private String direccion;
	private String telefono;
	private String pie;
	private String path;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDiocesis() {
		return diocesis;
	}
	public void setDiocesis(String diocesis) {
		this.diocesis = diocesis;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}
	public String getPath() {
		return path;
	}
}
