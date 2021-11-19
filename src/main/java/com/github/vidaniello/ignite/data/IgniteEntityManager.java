package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.util.Collection;

import javax.cache.CacheException;

/**
 * Interface for entities
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
public interface IgniteEntityManager extends Serializable {

	public <T extends Serializable> T create(T entity) throws CacheException, Exception;
	
	public <T extends Serializable> T remove(T entity) throws CacheException, Exception;
	
	public <T extends Serializable> T update(T entity) throws CacheException, Exception;
	
	public <T extends Serializable> T delete(T entity) throws CacheException, Exception;
	
	public <T extends Serializable> Collection<T> query(T entity) throws CacheException, Exception;
	
}
