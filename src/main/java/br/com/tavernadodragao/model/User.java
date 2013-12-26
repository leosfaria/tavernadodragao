package br.com.tavernadodragao.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="Username deve ter de 3 caracteres a 30")
	@Size(min=3, max=30, message="Username deve ter de 3 caracteres a 30")
	private String username;
	
	@NotBlank(message="Password deve ter de 3 a 15 caracteres")
	@Size(min=3,max=15, message="Password deve ter de 3 a 15 caracteres")
	private String password;
	
	@Transient
	@NotBlank(message="Confirm Password deve ter de 3 a 15 caracteres")
	@Size(min=3,max=15, message="Confirm Password deve ter de 3 a 15 caracteres")
	private String confirmPassword;
	
	@NotBlank(message="Email deve ser um email válido")
	private String email;

	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinTable(name="User_Friends")
	private Collection<User> friends;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<User> getFriends() {
		return (List<User>) friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
}
