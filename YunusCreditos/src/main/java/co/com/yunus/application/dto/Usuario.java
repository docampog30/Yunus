package co.com.yunus.application.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
@NamedQueries({
	@NamedQuery(name=Usuario.FIND_BY_USER_PASSWORD,query="select u from Usuario u where u.user = :user and u.password= :password")
})
public class Usuario {
	
	public static final String FIND_BY_USER_PASSWORD = "Usuario.findByUserPassword";
	@Id
	private String user;
	private String password;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
