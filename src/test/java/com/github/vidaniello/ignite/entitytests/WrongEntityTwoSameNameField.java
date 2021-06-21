package com.github.vidaniello.ignite.entitytests;

import java.io.Serializable;
import java.util.Date;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

/**
 * Example annotated @Entity with wrong two same name lowecase fields
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
@Entity
public class WrongEntityTwoSameNameField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PrimaryKey
	private Long id;
	
	private String anyField;
	private Long anotherField;
	
	private Date sameNameField;
	private Date sameNameFIeld;
	
	public WrongEntityTwoSameNameField() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Date getSameNameField() {
		return sameNameField;
	}
	
	public void setSameNameField(Date sameNameField) {
		this.sameNameField = sameNameField;
	}
	
	public Date getSameNameFIeld() {
		return sameNameFIeld;
	}
	
	public void setSameNameFIeld(Date sameNameFIeld) {
		this.sameNameFIeld = sameNameFIeld;
	}
	
}
