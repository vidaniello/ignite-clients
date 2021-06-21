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
public class WrongEntityTwoPrimaryKeySubEntity extends EntityFatherOfWrongEntities {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PrimaryKey
	private Long anotherId;
	
	private String anyField;
	private Long anotherField;
	
	public WrongEntityTwoPrimaryKeySubEntity() {
		
	}

	public Long getAnotherId() {
		return anotherId;
	}

	public void setAnotherId(Long anotherId) {
		this.anotherId = anotherId;
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
