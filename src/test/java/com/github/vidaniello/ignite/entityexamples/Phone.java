package com.github.vidaniello.ignite.entityexamples;

import java.io.Serializable;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private String imei;
	
	@PrimaryKey
	private String dd;

}
