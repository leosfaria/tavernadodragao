package br.com.tavernadodragao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Campaign {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message="O nome da campanha deve ter de 3 caracteres a 30")
	@Size(min=3, max=30, message="O nome da campanha deve ter de 3 caracteres a 30")
	private String name;
	
	private Long masterId;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Campaign_Players", joinColumns={@JoinColumn(name="Campaign_ID")}, inverseJoinColumns={@JoinColumn(name="User_ID")})
	private List<User> players;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public Long getMaster() {
		return masterId;
	}

	public void setMaster(Long masterId) {
		this.masterId = masterId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}
}
