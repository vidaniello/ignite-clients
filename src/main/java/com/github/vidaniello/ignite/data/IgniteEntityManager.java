package com.github.vidaniello.ignite.data;

import java.io.Serializable;

import javax.cache.CacheException;

public interface IgniteEntityManager extends Serializable {

	public <T extends Serializable> T create(T entity) throws CacheException, Exception;
	
}
