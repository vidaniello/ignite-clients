package com.github.vidaniello.ignite.entitytests;

import java.io.Serializable;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class WrongEntityWithNotSerializableField implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private SerializablePrimaryKey id;
	
	private String aField;
	
	private NotSerializableObject field;
	
	public WrongEntityWithNotSerializableField() {
		
	}
	
	public String getaField() {
		return aField;
	}
	
	public void setaField(String aField) {
		this.aField = aField;
	}
	
	public SerializablePrimaryKey getId() {
		return id;
	}
	
	public void setId(SerializablePrimaryKey id) {
		this.id = id;
	}

	public NotSerializableObject getField() {
		return field;
	}
	
	public void setField(NotSerializableObject field) {
		this.field = field;
	}
}
