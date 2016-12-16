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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="VINCULACION")
public class Vinculacion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String entidadahorro;
	private Long vlrahorro;
	private String entidadcesantias;
	private Long vlrcesantias;
	private Long vlrotros;
	private Long subsidiocaja;
	private Long capacidaddeuda;
	private String nombreempresa;
	private Date feingreso;
	private Long tipoempresa;
	private Long tipovivienda;
	private String cargo;
	private String direcciontrabajo;
	private String ciudadtrabajo;
	private String barrio;
	private Long ingresos;
	private Long egresos;
	private Long otrosingresos;
	private Long ingresossalario;
	private String isdeudas;
	@Temporal(TemporalType.DATE)
	private Date fecorte;
	private String isdeclarante;
	private Long pagocooperativa;
	private Long cuota;
	private String origen;
	private String referencia1;
	private String dirreferencia1;
	private String telreferencia1;
	private String referenciaf;
	private String dirreferenciaf;
	private String telreferenciaf;
	private String parentescoreferencia;
	private String referenciacoop;
	private Long referenciacoopafi;
	private String referenciacooptel;
	private Long referenciacoopafiM;
	private String lugarentrevista;
	@Temporal(TemporalType.DATE)
	private Date feentrevista;
	private String resultadoentrevista;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="vinculacion",fetch=FetchType.EAGER)
	private List<Beneficiario> beneficiarios;
	
	private Long idCliente;
	private String iscajacompensacion;
	private String issubsidiomunicipal;
	private String issubsidiodepartamental;
	private Long vlrsubsidiom;
	private Long vlrsubsidiod;
	private Long montodeuda;
	private String entidaddeuda;
	private Long pasivos;
	private Long vigencia;
	
	@ManyToOne
	@JoinColumn(name="IDCLIENTE",insertable=false,updatable=false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="TIPOEMPRESA",insertable=false,updatable=false)
	private Maestro tipoempresaMaestro;

	@ManyToOne
	@JoinColumn(name="TIPOVIVIENDA",insertable=false,updatable=false)
	private Maestro tipoviviendaMaestro;

	@ManyToOne
	@JoinColumn(name="REFERENCIACOOPAFI",insertable=false,updatable=false)
	private Maestro referenciaafinidadMaestro;
	
	public String getEntidadahorro() {
		return entidadahorro;
	}
	public void setEntidadahorro(String entidadahorro) {
		this.entidadahorro = entidadahorro;
	}
	public Long getVlrahorro() {
		return vlrahorro;
	}
	public void setVlrahorro(Long vlrahorro) {
		this.vlrahorro = vlrahorro;
	}
	public String getEntidadcesantias() {
		return entidadcesantias;
	}
	public void setEntidadcesantias(String entidadcesantias) {
		this.entidadcesantias = entidadcesantias;
	}
	public Long getVlrcesantias() {
		return vlrcesantias;
	}
	public void setVlrcesantias(Long vlrcesantias) {
		this.vlrcesantias = vlrcesantias;
	}
	public Long getVlrotros() {
		return vlrotros;
	}
	public void setVlrotros(Long vlrotros) {
		this.vlrotros = vlrotros;
	}
	public Long getCapacidaddeuda() {
		return capacidaddeuda;
	}
	public void setCapacidaddeuda(Long capacidaddeuda) {
		this.capacidaddeuda = capacidaddeuda;
	}
	public String getNombreempresa() {
		return nombreempresa;
	}
	public void setNombreempresa(String nombreempresa) {
		this.nombreempresa = nombreempresa;
	}
	public Date getFeingreso() {
		return feingreso;
	}
	public void setFeingreso(Date feingreso) {
		this.feingreso = feingreso;
	}
	public Long getTipoempresa() {
		return tipoempresa;
	}
	public void setTipoempresa(Long tipoempresa) {
		this.tipoempresa = tipoempresa;
	}
	public Long getTipovivienda() {
		return tipovivienda;
	}
	public void setTipovivienda(Long tipovivienda) {
		this.tipovivienda = tipovivienda;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDirecciontrabajo() {
		return direcciontrabajo;
	}
	public void setDirecciontrabajo(String direcciontrabajo) {
		this.direcciontrabajo = direcciontrabajo;
	}
	public String getCiudadtrabajo() {
		return ciudadtrabajo;
	}
	public void setCiudadtrabajo(String ciudadtrabajo) {
		this.ciudadtrabajo = ciudadtrabajo;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public Long getIngresos() {
		return ingresos;
	}
	public void setIngresos(Long ingresos) {
		this.ingresos = ingresos;
	}
	public Long getEgresos() {
		return egresos;
	}
	public void setEgresos(Long egresos) {
		this.egresos = egresos;
	}
	public Long getOtrosingresos() {
		return otrosingresos;
	}
	public void setOtrosingresos(Long otrosingresos) {
		this.otrosingresos = otrosingresos;
	}
	public Long getIngresossalario() {
		return ingresossalario;
	}
	public void setIngresossalario(Long ingresossalario) {
		this.ingresossalario = ingresossalario;
	}
	public String getIsdeudas() {
		return isdeudas;
	}
	public void setIsdeudas(String isdeudas) {
		this.isdeudas = isdeudas;
	}
	public Date getFecorte() {
		return fecorte;
	}
	public void setFecorte(Date fecorte) {
		this.fecorte = fecorte;
	}
	public String getIsdeclarante() {
		return isdeclarante;
	}
	public void setIsdeclarante(String isdeclarante) {
		this.isdeclarante = isdeclarante;
	}
	public Long getPagocooperativa() {
		return pagocooperativa;
	}
	public void setPagocooperativa(Long pagocooperativa) {
		this.pagocooperativa = pagocooperativa;
	}
	public Long getCuota() {
		return cuota;
	}
	public void setCuota(Long cuota) {
		this.cuota = cuota;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	public String getDirreferencia1() {
		return dirreferencia1;
	}
	public void setDirreferencia1(String dirreferencia1) {
		this.dirreferencia1 = dirreferencia1;
	}
	public String getTelreferencia1() {
		return telreferencia1;
	}
	public void setTelreferencia1(String telreferencia1) {
		this.telreferencia1 = telreferencia1;
	}
	public String getReferenciaf() {
		return referenciaf;
	}
	public void setReferenciaf(String referenciaf) {
		this.referenciaf = referenciaf;
	}
	public String getDirreferenciaf() {
		return dirreferenciaf;
	}
	public void setDirreferenciaf(String dirreferenciaf) {
		this.dirreferenciaf = dirreferenciaf;
	}
	public String getTelreferenciaf() {
		return telreferenciaf;
	}
	public void setTelreferenciaf(String telreferenciaf) {
		this.telreferenciaf = telreferenciaf;
	}
	public String getParentescoreferencia() {
		return parentescoreferencia;
	}
	public void setParentescoreferencia(String parentescoreferencia) {
		this.parentescoreferencia = parentescoreferencia;
	}
	public String getReferenciacoop() {
		return referenciacoop;
	}
	public void setReferenciacoop(String referenciacoop) {
		this.referenciacoop = referenciacoop;
	}
	public String getReferenciacooptel() {
		return referenciacooptel;
	}
	public void setReferenciacooptel(String referenciacooptel) {
		this.referenciacooptel = referenciacooptel;
	}
	public Long getReferenciacoopafiM() {
		return referenciacoopafiM;
	}
	public void setReferenciacoopafiM(Long referenciacoopafiM) {
		this.referenciacoopafiM = referenciacoopafiM;
	}
	public String getLugarentrevista() {
		return lugarentrevista;
	}
	public void setLugarentrevista(String lugarentrevista) {
		this.lugarentrevista = lugarentrevista;
	}
	public Date getFeentrevista() {
		return feentrevista;
	}
	public void setFeentrevista(Date feentrevista) {
		this.feentrevista = feentrevista;
	}
	public String getResultadoentrevista() {
		return resultadoentrevista;
	}
	public void setResultadoentrevista(String resultadoentrevista) {
		this.resultadoentrevista = resultadoentrevista;
	}
	public List<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(List<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getIscajacompensacion() {
		return iscajacompensacion;
	}
	public void setIscajacompensacion(String iscajacompensacion) {
		this.iscajacompensacion = iscajacompensacion;
	}
	public String getIssubsidiomunicipal() {
		return issubsidiomunicipal;
	}
	public void setIssubsidiomunicipal(String issubsidiomunicipal) {
		this.issubsidiomunicipal = issubsidiomunicipal;
	}
	public String getIssubsidiodepartamental() {
		return issubsidiodepartamental;
	}
	public void setIssubsidiodepartamental(String issubsidiodepartamental) {
		this.issubsidiodepartamental = issubsidiodepartamental;
	}
	public Long getVlrsubsidiom() {
		return vlrsubsidiom;
	}
	public void setVlrsubsidiom(Long vlrsubsidiom) {
		this.vlrsubsidiom = vlrsubsidiom;
	}
	public Long getVlrsubsidiod() {
		return vlrsubsidiod;
	}
	public void setVlrsubsidiod(Long vlrsubsidiod) {
		this.vlrsubsidiod = vlrsubsidiod;
	}
	public Long getMontodeuda() {
		return montodeuda;
	}
	public void setMontodeuda(Long montodeuda) {
		this.montodeuda = montodeuda;
	}
	public String getEntidaddeuda() {
		return entidaddeuda;
	}
	public void setEntidaddeuda(String entidaddeuda) {
		this.entidaddeuda = entidaddeuda;
	}
	public Long getPasivos() {
		return pasivos;
	}
	public void setPasivos(Long pasivos) {
		this.pasivos = pasivos;
	}
	public Long getReferenciacoopafi() {
		return referenciacoopafi;
	}
	public void setReferenciacoopafi(Long referenciacoopafi) {
		this.referenciacoopafi = referenciacoopafi;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	public Long getSubsidiocaja() {
		return subsidiocaja;
	}
	public void setSubsidiocaja(Long subsidiocaja) {
		this.subsidiocaja = subsidiocaja;
	}
	public Long getVigencia() {
		return vigencia;
	}
	public void setVigencia(Long vigencia) {
		this.vigencia = vigencia;
	}
	
}
