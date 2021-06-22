package com.github.vidaniello.ignite.entitytests1;

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
public class EntityFatherOfWrongEntities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@PrimaryKey
	private Long id;
	
	private String aStringField;
	
	private Long duplicateThisFieldName;
	
	public EntityFatherOfWrongEntities() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getaStringField() {
		return aStringField;
	}
	
	public void setaStringField(String aStringField) {
		this.aStringField = aStringField;
	}
	
	public Long getDuplicateThisFieldName() {
		return duplicateThisFieldName;
	}
	
	public void setDuplicateThisFieldName(Long duplicateThisFieldName) {
		this.duplicateThisFieldName = duplicateThisFieldName;
	}
	
}
