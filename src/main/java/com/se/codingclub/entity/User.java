package com.se.codingclub.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Pattern(regexp = "^[a-zA-Z0-9]{4,30}$",message = "At least 4 chars/digits and can't more than 30 chars/digits")
	@Column(name = "username")
	private String userName;
	
	@Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",message = "At least 6 chars/digits and can't more than 20 chars/digits")
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "address")
	private String address;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "role")
	private String role;
	@Column(name = "create_date")
	private Date createdDate;
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public User(String userName, String password, String firstName, String lastName, String address, String telephone) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telephone = telephone;
	}
	
	
	public User(String userName, String password, String firstName, String lastName, String address,
			String telephone, String role, Date createdDate, Date modifiedDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telephone = telephone;
		this.role = role;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public User(int id, String userName, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.role = role;
	}
	

	public User(String userName, String password, Date createdDate, Date modifiedDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}


	@OneToMany(mappedBy = "user")
	private List<ShoppingSession> shoppingSessions;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", telephone=" + telephone + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
