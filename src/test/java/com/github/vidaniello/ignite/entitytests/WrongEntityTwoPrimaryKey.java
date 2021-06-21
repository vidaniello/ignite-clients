package com.github.vidaniello.ignite.entitytests;

import java.io.Serializable;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

/**
 * Example annotated @Entity with wrong two annotated @PrimaryKey
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
@Entity
public class WrongEntityTwoPrimaryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PrimaryKey
	private Long id;
	
	@PrimaryKey
	private String id2;
	
	private String anyField;
	private Long anotherField;
	
	public WrongEntityTwoPrimaryKey() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getAnyField() {
		return anyField;
	}
	
	public void setAnyField(String anyField) {
		this.anyField = anyField;
	}

	public Long getAnotherField() {
		return anotherField;
	}
	
	public void setAnotherField(Long anotherField) {
		this.anotherField = anotherField;
	}
	
}
