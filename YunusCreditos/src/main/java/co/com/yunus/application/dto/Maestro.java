package co.com.yunus.application.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="MAESTROS")
@NamedQueries({
	@NamedQuery(
            name=Maestro.FIND_BY_PARENT,
            query="SELECT c FROM Maestro c WHERE c.idParent = :idparent"
    )

})
public class Maestro {

	public static final String FIND_BY_PARENT = "findByParent";
	@Id
	private Long id;
	private String descripcion;
	private String codigo;
	@Column(name="PARENT_ID",nullable=true)
	private Long idParent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdParent() {
		return idParent;
	}
	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}
}
