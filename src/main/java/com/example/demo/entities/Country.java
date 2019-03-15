package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Country {
	
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String capital;
  
  
	public Country(String name, String capital) {
		this.name = name;
		this.capital = capital;
	}
	
	public Country() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "Contry [id=" + id + ", name=" + name + ", capital=" + capital + "]";
	}
	  
  
}
