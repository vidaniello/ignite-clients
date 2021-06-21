package com.github.vidaniello.ignite.entitytests;

import com.github.vidaniello.ignite.data.Entity;

/**
 * Example annotated @Entity with wrong two same name lowecase fields
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
@Entity
public class WrongEntityTwoSameNameFieldSubEntity extends EntityFatherOfWrongEntities {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String anyField;
	private Long anotherField;
	
	private Long duplicateThisFieldName;
	
	public WrongEntityTwoSameNameFieldSubEntity() {
		
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
	
	public Long getDuplicateThisFieldName() {
		return duplicateThisFieldName;
	}
	
	public void setDuplicateThisFieldName(Long duplicateThisFieldName) {
		this.duplicateThisFieldName = duplicateThisFieldName;
	}
}
