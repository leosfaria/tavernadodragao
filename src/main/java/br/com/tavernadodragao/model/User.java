package br.com.tavernadodragao.model;

import java.util.ArrayList;
import java.util.Date;
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
	
	@NotBlank(message="Confirm Password deve ter de 3 a 15 caracteres")
	@Size(min=3,max=15, message="Confirm Password deve ter de 3 a 15 caracteres")
	private String confirmPassword;
	
	@NotBlank(message="Email deve ser um email válido")
	private String email;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_Friends", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Friend_ID")})
	private List<User> friends = new ArrayList<User>();

	@ManyToMany(mappedBy="friends")
	private List<User> users;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_Requests", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Friend_ID")})
	private List<User> friendsRequests = new ArrayList<User>();

	@OneToMany(mappedBy="friendsRequests")
	private List<User> usersRequests;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_Campaign", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Campaign_ID")})
	private List<Campaign> campaigns;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_MessageReceived", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Message_ID")})
	private List<Message> messagesReceived;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_MessagePending", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Message_ID")})
	private List<Message> messagesPendings;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinTable(name="User_Charactersheet", joinColumns={@JoinColumn(name="User_ID")}, inverseJoinColumns={@JoinColumn(name="Charactersheet_ID")})
	private List<Charactersheet> charactersheets;
	
	private Date timeLogged;
	
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	public List<User> getFriendsRequests() {
		return friendsRequests;
	}
	public void setFriendsRequests(List<User> friendsRequests) {
		this.friendsRequests = friendsRequests;
	}
	public List<User> getUsersRequests() {
		return usersRequests;
	}
	public void setUsersRequests(List<User> usersRequests) {
		this.usersRequests = usersRequests;
	}
	public Date getTimeLogged() {
		return timeLogged;
	}
	public void setTimeLogged(Date timeLogged) {
		this.timeLogged = timeLogged;
	}
	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}
	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}
	public List<Message> getMessagesPendings() {
		return messagesPendings;
	}
	public void setMessagesPendings(List<Message> messagesPendings) {
		this.messagesPendings = messagesPendings;
	}
	public List<Charactersheet> getCharactersheets() {
		return charactersheets;
	}
	public void setCharactersheets(List<Charactersheet> charactersheets) {
		this.charactersheets = charactersheets;
	}
}
