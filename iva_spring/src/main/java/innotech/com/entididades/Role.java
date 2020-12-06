package innotech.com.entididades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "authority"} )} )
public class Role implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	private Usuarios user_id;
	
	private String authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getauthority() {
		return authority;
	}

	public void setauthority(String autority) {
		this.authority = autority;
	}

	public Usuarios getUser_id() {
		return user_id;
	}

	public void setUser_id(Usuarios user_id) {
		this.user_id = user_id;
	}
	
	
}
