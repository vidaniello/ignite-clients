package com.github.vidaniello.ignite.entitytests1;

import java.io.Serializable;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class WrongEntityWithNotSerializablePrimaryKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private NotSerializablePrimaryKey id;
	
	private String aField;
	
	public WrongEntityWithNotSerializablePrimaryKey() {
		
	}
	
	public NotSerializablePrimaryKey getId() {
		return id;
	}
	
	public void setId(NotSerializablePrimaryKey id) {
		this.id = id;
	}
	
	public String getaField() {
		return aField;
	}
	
	public void setaField(String aField) {
		this.aField = aField;
	}
	


}
