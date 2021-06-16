package com.github.vidaniello.ignite;

import org.apache.ignite.configuration.CacheConfiguration;

import com.github.vidaniello.ignite.data.IgniteEntityImpl;

public class Person extends IgniteEntityImpl<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	public Person() {
		
	}
	
	public Person(String name) {
		
		this.name = name;
	}



	public String getName() {
		return name;
	}
	
	public Person setName(String name) {
		this.name=name;
		return this;
	}

	
	@Override
	public String toString() {
		return getId()+" "+getName();
	}

	@Override
	public CacheConfiguration<Integer, Person> getDefaultCacheConfiguration() {
		
		return null;
	}

	
}
