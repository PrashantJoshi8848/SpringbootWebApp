package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="category_id")
private int id;

public int getId() {
	return id;
}

public void setId(int id) 
{
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

private String name;

}