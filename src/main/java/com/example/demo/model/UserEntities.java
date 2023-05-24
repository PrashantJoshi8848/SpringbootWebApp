package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserEntities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String lastName;
	@Column(nullable = false,unique = true)
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = {@JoinColumn(name= "USER_ID",referencedColumnName="Id")},
	inverseJoinColumns= {@JoinColumn(name = "Role_Id",referencedColumnName = "Id")}
	)
	private List<RoleEntities> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleEntities> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntities> roles) {
		this.roles = roles;
	}

	public UserEntities(UserEntities userEntities) {
		super();
		this.id =userEntities.getId();
		this.firstName =userEntities.getFirstName();
		this.lastName = userEntities.getLastName();
		this.email = userEntities.getEmail();
		this.password = userEntities.getPassword();
		this.roles = userEntities.getRoles();
	}
	public UserEntities() {
		
	}

}
