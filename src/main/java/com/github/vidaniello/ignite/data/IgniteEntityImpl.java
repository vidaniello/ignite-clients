package com.github.vidaniello.ignite.data;

import java.io.Serializable;

import org.apache.ignite.configuration.CacheConfiguration;

/**
 * Abstract implementation of IgniteEntity interface.<br>
 * Take care of entity 'field names': <a href="https://ignite.apache.org/docs/latest/key-value-api/binary-objects">binary-objects Restrictions</a><br>
 * RESERVED FIELD NAMES:<b>
 * <ul>
 * <li>_key_: It's the primary key filed</li>
 * </ul>
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
public abstract class IgniteEntityImpl<PRIMARY_KEY extends Serializable> implements IgniteEntity<PRIMARY_KEY>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	{
		//Dinamyc agent
		System.out.println("Ragiu");
		
	}
	*/
	
	//private Map<Serializable,Serializable> data;
	private PRIMARY_KEY _key_;
	
	/*
	public final void save() {
		
	}
	
	public final <T> T get() {
		return null;
	}
	*/
	
	@Override
	public final PRIMARY_KEY getId() {
		return _key_;
	}
	
	public abstract CacheConfiguration<PRIMARY_KEY, ? extends IgniteEntity<PRIMARY_KEY>> getDefaultCacheConfiguration();

}
