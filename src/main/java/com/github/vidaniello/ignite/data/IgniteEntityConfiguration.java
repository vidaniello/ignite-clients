package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.util.Date;

import org.apache.ignite.configuration.CacheConfiguration;

public class IgniteEntityConfiguration<KEY extends Serializable, VALUE extends IgniteEntity<KEY>> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyClassCanonicalName;
	private String managedClassCanonicalName;
	private CacheConfiguration<KEY, VALUE> igniteCacheConfiguration;
	
	public IgniteEntityConfiguration(Class<KEY> keyClass, Class<VALUE> managedClass) {
		this.keyClassCanonicalName = keyClass.getCanonicalName();
		this.managedClassCanonicalName = managedClass.getCanonicalName();
		igniteCacheConfiguration = new CacheConfiguration<>(managedClass.getCanonicalName());
	}

	
	
	public CacheConfiguration<KEY, VALUE> getIgniteCacheConfiguration() {
		return igniteCacheConfiguration;
	}

	public String getManagedClassCanonicalName() {
		return managedClassCanonicalName;
	}
	
	public String getKeyClassCanonicalName() {
		return keyClassCanonicalName;
	}

	
}
