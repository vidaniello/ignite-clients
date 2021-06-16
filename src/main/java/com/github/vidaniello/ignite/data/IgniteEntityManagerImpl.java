package com.github.vidaniello.ignite.data;

import java.io.Serializable;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class IgniteEntityManagerImpl implements IgniteEntityManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Ignite ignite;
	
	
	@SuppressWarnings("resource")
	public <T extends Serializable> T create(T entity){
		
		IgniteCache<Serializable,T> cache = ignite.cache(entity.getClass().getCanonicalName());
		
		if(cache!=null) {
			cache.put(entity, entity);
		}
		
		return entity;
	}
	
}
