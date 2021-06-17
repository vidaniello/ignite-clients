package com.github.vidaniello.ignite.data;

import java.io.Serializable;

import javax.cache.CacheException;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;

import com.github.vidaniello.ignite.ClientProvider;

public class IgniteEntityManagerImpl implements IgniteEntityManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private Ignite ignite;
	
	
	@SuppressWarnings("resource")
	public <T extends Serializable> T create(T entity) throws CacheException, Exception{
		
		IgniteCache<Serializable,T> cache = ClientProvider.instance().ignite().cache(entity.getClass().getCanonicalName());
		
		if(cache==null) 
			ClientProvider.instance().ignite().addCacheConfiguration(null);		
		
		cache.put(loadId(entity), entity);
		
		return entity;
	}
	
	
	private Serializable loadId(Serializable entity) throws IgniteException, Exception {
		Long id = ClientProvider.instance().ignite().atomicSequence(entity.getClass().getCanonicalName(), 0, true).incrementAndGet();
		//Field ifField = entity.getClass().getF
		return id;
	}
}
