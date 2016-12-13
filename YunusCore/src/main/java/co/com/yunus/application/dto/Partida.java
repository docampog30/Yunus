package co.com.yunus.application.dto;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import co.com.yunus.application.enums.TipoSacramento;

@Entity
@Table(name="PARTIDAS")
@Cacheable(false)
@Cache(usage=CacheConcurrencyStrategy.NONE)
@NamedQueries({
@NamedQuery(name="findByP1",query="SELECT p FROM Partida p WHERE persona1.cedula LIKE :cedula"),
@NamedQuery(name="findByP2",query="SELECT p FROM Partida p WHERE persona2.cedula LIKE :cedula"),
@NamedQuery(name="findByNumeroAndTipo",query="SELECT p FROM Partida p WHERE p.numero = :numero and p.tipo = :tipo"),
@NamedQuery(name="findById",query="SELECT p FROM Partida p WHERE p.id = :id"),

})
public class Partida {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long			id;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="persona1_id",referencedColumnName="id")
	private Persona 		persona1;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="persona2_id",referencedColumnName="id",nullable=true)
	private Persona 		persona2;
	
	@Enumerated(EnumType.STRING)
	private TipoSacramento 	tipo;
	private Date	 		fecha;
	private String 			testigos;
	
	@ManyToOne
	@JoinColumn(name="ministro_id",referencedColumnName="id",insertable=false,updatable=false)
	private Persona		ministro;
	
	@Column(name="MINISTRO_ID")
	private Long idMinistro;
	
	@Column(name="LIBRO_NRO")
	private String			libroNro;
	@Column(name="ANIO_PARTIDA")
	private String 			anioPartida;
	private String 			folio;
	private String 			numero;
	private String 			nota;
	private String 			padrino1;
	private String 			padrino2;
	private String			obispo;
	private String	 		pabautizmo;
	
	public Persona getPersona1() {
		return persona1;
	}
	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}
	public Persona getPersona2() {
		return persona2;
	}
	public void setPersona2(Persona persona2) {
		this.persona2 = persona2;
	}
	public TipoSacramento getTipo() {
		return tipo;
	}
	public void setTipo(TipoSacramento tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTestigos() {
		return testigos;
	}
	public void setTestigos(String testigos) {
		this.testigos = testigos;
	}
	public Persona getMinistro() {
		return ministro;
	}
	public void setMinistro(Persona ministro) {
		this.ministro = ministro;
	}
	public String getLibroNro() {
		return libroNro;
	}
	public void setLibroNro(String libroNro) {
		this.libroNro = libroNro;
	}
	public String getAnioPartida() {
		return anioPartida;
	}
	public void setAnioPartida(String anioPartida) {
		this.anioPartida = anioPartida;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public Long getIdMinistro() {
		return idMinistro;
	}
	
	public void setIdMinistro(Long idMinistro) {
		this.idMinistro = idMinistro;
	}
	public String getPadrino1() {
		return padrino1;
	}
	public void setPadrino1(String padrino1) {
		this.padrino1 = padrino1;
	}
	public String getPadrino2() {
		return padrino2;
	}
	public void setPadrino2(String padrino2) {
		this.padrino2 = padrino2;
	}
	public String getObispo() {
		return obispo;
	}
	public void setObispo(String obispo) {
		this.obispo = obispo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPabautizmo() {
		return pabautizmo;
	}
	public void setPabautizmo(String pabautizmo) {
		this.pabautizmo = pabautizmo;
	}
}
