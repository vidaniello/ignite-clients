package com.github.vidaniello.ignite.entitytests2;

import java.io.Serializable;

public class PrimaryKeyWithFieldsSerializable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer firstKey;
	private FieldSerializable secondKey;
	
	public PrimaryKeyWithFieldsSerializable() {
		
	}
	
	public Integer getFirstKey() {
		return firstKey;
	}
	
	public void setFirstKey(Integer firstKey) {
		this.firstKey = firstKey;
	}
	
	public FieldSerializable getSecondKey() {
		return secondKey;
	}
	
	public void setSecondKey(FieldSerializable secondKey) {
		this.secondKey = secondKey;
	}
	
}
