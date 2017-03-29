package co.com.yunus.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name=Detalle.FINDBYBETWEENDATE,query="SELECT e FROM Detalle e WHERE e.fechapago BETWEEN :startDate AND :endDate"),
	@NamedQuery(name=Detalle.FINDMOROSOSBYBETWEENDATE,query="SELECT s FROM Detalle s  WHERE s.estado = 'VENCIDA' AND s.fecha BETWEEN :startDate AND :endDate")
	})
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FINDBYBETWEENDATE = "findBetweenDate";
	public static final String FINDMOROSOSBYBETWEENDATE = "findBetweenDateMorosos";	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal amortizacion;

	private BigDecimal cuota;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechapago;

	private BigDecimal intereses;

	private int periodo;

	private BigDecimal saldofinal;

	private BigDecimal saldoinicial;
	
	@ManyToOne
	@JoinColumn(name="IDCREDITO",updatable=false)
	private Credito credito;
	
	private BigDecimal valorpagado;

	public Detalle() {
	}

	public int getId() {
		return this.id;
	}
	
	@JsonIgnore
	public String getCliente(){
		return credito.getCliente().getNombres().concat(" ").concat(credito.getCliente().getApellidos());
	}
	
	@JsonIgnore
	public String getDocumentoCliente(){
		return credito.getCliente().getDocumento();
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmortizacion() {
		return this.amortizacion;
	}

	public void setAmortizacion(BigDecimal amortizacion) {
		this.amortizacion = amortizacion;
	}

	public BigDecimal getCuota() {
		return this.cuota;
	}

	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getIntereses() {
		return this.intereses;
	}

	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getSaldofinal() {
		return this.saldofinal;
	}

	public void setSaldofinal(BigDecimal saldofinal) {
		this.saldofinal = saldofinal;
	}

	public BigDecimal getSaldoinicial() {
		return this.saldoinicial;
	}

	public void setSaldoinicial(BigDecimal saldoinicial) {
		this.saldoinicial = saldoinicial;
	}

	public Credito getCredito() {
		return this.credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}
	public Date getFechapago() {
		return fechapago;
	}
	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}
	
	@JsonIgnore
	public int getidcredito(){
		return this.credito.getId();
	}

	public BigDecimal getValorpagado() {
		return valorpagado;
	}

	public void setValorpagado(BigDecimal valorpagado) {
		this.valorpagado = valorpagado;
	}
}