package co.com.yunus.application.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the credito database table.
 * 
 */
@Entity
@NamedQuery(name=Credito.FIND_BY_DOCUMENTO_CLIENTE, query="SELECT c FROM Credito c where c.cliente.documento = :documento")
public class Credito implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_DOCUMENTO_CLIENTE = "findByCliente";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name="IDCLIENTE",insertable=false,updatable=false)
	private Cliente cliente;
	
	private int idcliente;

	private float interes;

	private int plazo;

	private float valor;

	@OneToMany(mappedBy="credito",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Detalle> detalles;

	public Credito() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public float getInteres() {
		return this.interes;
	}

	public void setInteres(float interes) {
		this.interes = interes;
	}

	public int getPlazo() {
		return this.plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setCredito(this);

		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setCredito(null);

		return detalle;
	}

}