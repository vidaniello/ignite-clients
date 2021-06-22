package com.github.vidaniello.ignite.entitytests2;

import java.io.Serializable;

public class FieldSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String aField;
	
	public FieldSerializable() {
		
	}
	
	public String getaField() {
		return aField;
	}
	 
	public void setaField(String aField) {
		this.aField = aField;
	}
}
