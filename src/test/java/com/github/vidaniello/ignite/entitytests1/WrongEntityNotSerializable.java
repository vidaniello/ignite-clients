package com.github.vidaniello.ignite.entitytests1;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

/**
 * Example annotated @Entity who not implements Serializable interface.
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
@Entity
public class WrongEntityNotSerializable {

	@PrimaryKey
	private Integer id;
	private String a_field;
	
	public WrongEntityNotSerializable() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getA_field() {
		return a_field;
	}

	public void setA_field(String a_field) {
		this.a_field = a_field;
	}
	
	
}
