package com.example.demo.model;

import java.util.List;

import org.apache.catalina.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_role")
public class RoleEntities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntities> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntities> users) {
		this.users = users;
	}

	@Column(nullable = false,unique = true)
	@NotEmpty
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<UserEntities> users;
	
	
}
