package br.com.tavernadodragao.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@javax.persistence.Table(name="Campaign")
public class Table {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="O nome da mesa deve ter de 3 caracteres a 30")
	@Size(min=3, max=30, message="O nome da mesa deve ter de 3 caracteres a 30")
	private String name;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_Campaign")
	private Collection<User> users = new ArrayList<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
