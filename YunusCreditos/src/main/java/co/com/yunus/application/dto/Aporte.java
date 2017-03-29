package co.com.yunus.application.dto;

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
	@NamedQuery(name=Aporte.FINDBYCLIENTE,query="select a from Aporte a where cliente.documento= :documento"),
	@NamedQuery(name=Aporte.FINDBYBETWEENDATE,query="SELECT e FROM Aporte e WHERE e.fecha BETWEEN :startDate AND :endDate"),
	@NamedQuery(name=Aporte.FINDBYID,query="select a from Aporte a where a.id = :id")
})
public class Aporte {
	public static final String FINDBYCLIENTE = "Aporte.findByCliente";
	public static final String FINDBYID = "Aporte.findById";
	public static final String FINDBYBETWEENDATE = "Aporte.findBetweenDate";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private BigDecimal valor;
	private long idcliente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="IDCLIENTE",insertable=false,updatable=false)
	private Cliente cliente;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(long idcliente) {
		this.idcliente = idcliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@JsonIgnore
	public String getClienteName() {
		return cliente.getNombres() + " "+cliente.getApellidos();
	}
	

	@JsonIgnore
	public String getClienteDocumento() {
		return cliente.getDocumento();
	}
}
