package com.github.vidaniello.ignite.entityexamples;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@PrimaryKey
	private Integer id;
	
	private String name;
	private String surname;
	
	private Phone privatePhone;
	private Collection<Phone> allPhones;
	private Collection<Phone> sharedPhones;
	
	
	public Person() {
		
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


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Phone getPrivatePhone() {
		return privatePhone;
	}


	public void setPrivatePhone(Phone privatePhone) {
		this.privatePhone = privatePhone;
	}


	public Collection<Phone> getAllPhones() {
		if(allPhones==null)
			allPhones = new HashSet<>();
		return allPhones;
	}


	public void setAllPhones(Collection<Phone> allPhones) {
		this.allPhones = allPhones;
	}


	public Collection<Phone> getSharedPhones() {
		return sharedPhones;
	}


	public void setSharedPhones(Collection<Phone> sharedPhones) {
		this.sharedPhones = sharedPhones;
	}

	
}
