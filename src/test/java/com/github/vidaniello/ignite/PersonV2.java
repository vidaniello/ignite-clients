package com.github.vidaniello.ignite;

import java.io.Serializable;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class PersonV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@PrimaryKey
	private Integer id;
	
	private String name;
	private String surname;

}
