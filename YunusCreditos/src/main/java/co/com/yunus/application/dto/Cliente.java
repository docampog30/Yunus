package co.com.yunus.application.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
@NamedQueries({
	@NamedQuery(name=Cliente.BUSCAR_POR_DOCUMENTO,query="SELECT C FROM Cliente C WHERE C.documento = :documento"),
	@NamedQuery(name=Cliente.BUSCAR_POR_ID,query="SELECT C FROM Cliente C WHERE C.id = :id")
})
public class Cliente {
	
	public static final String BUSCAR_POR_DOCUMENTO = "Cliente.buscarXDocumento";
	public static final String BUSCAR_POR_ID= "Cliente.buscarXId";
	
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
	
	@OneToMany(mappedBy="cliente",fetch=FetchType.EAGER)
	private List<Vinculacion> vinculaciones;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="IDACUDIENTE")
	private Cliente acudiente;
	
	@ManyToOne
	@JoinColumn(name="ESTADOCIVIL",insertable=false,updatable=false)
	private Maestro estadocivilMaestro;

	@ManyToOne
	@JoinColumn(name="NIVELEDUCATIVO",insertable=false,updatable=false)
	private Maestro niveleducativoMaestro;

	@ManyToOne
	@JoinColumn(name="TIPOVIVIENDA",insertable=false,updatable=false)
	private Maestro tipoviviendaMaestro;

	@ManyToOne
	@JoinColumn(name="ZONAVIVIENDA",insertable=false,updatable=false)
	private Maestro zonaviviendaMaestro;

	@ManyToOne
	@JoinColumn(name="OCUPACION",insertable=false,updatable=false)
	private Maestro ocupacionMaestro;
	
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
	public Maestro getEstadocivilMaestro() {
		return estadocivilMaestro;
	}
	public Maestro getNiveleducativoMaestro() {
		return niveleducativoMaestro;
	}
	public Maestro getTipoviviendaMaestro() {
		return tipoviviendaMaestro;
	}
	public Maestro getZonaviviendaMaestro() {
		return zonaviviendaMaestro;
	}
	public Maestro getOcupacionMaestro() {
		return ocupacionMaestro;
	}
	
	public Cliente getAcudiente() {
		return acudiente;
	}
	public void setAcudiente(Cliente acudiente) {
		this.acudiente = acudiente;
	}
	public void setEstadocivilMaestro(Maestro estadocivilMaestro) {
		this.estadocivilMaestro = estadocivilMaestro;
	}
	public void setNiveleducativoMaestro(Maestro niveleducativoMaestro) {
		this.niveleducativoMaestro = niveleducativoMaestro;
	}
	public void setTipoviviendaMaestro(Maestro tipoviviendaMaestro) {
		this.tipoviviendaMaestro = tipoviviendaMaestro;
	}
	public void setZonaviviendaMaestro(Maestro zonaviviendaMaestro) {
		this.zonaviviendaMaestro = zonaviviendaMaestro;
	}
	public void setOcupacionMaestro(Maestro ocupacionMaestro) {
		this.ocupacionMaestro = ocupacionMaestro;
	}
	public List<Vinculacion> getVinculaciones() {
		return vinculaciones;
	}
	
	public void setVinculaciones(List<Vinculacion> vinculaciones) {
		this.vinculaciones = vinculaciones;
	}
}
