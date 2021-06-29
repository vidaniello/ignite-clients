package com.github.vidaniello.ignite.data.relations;

import java.io.Serializable;
import com.github.vidaniello.ignite.data.IgniteEntityScanner;

import javassist.util.proxy.ProxyFactory;

public class RelationProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T getProxy(T entity, Serializable keyId) throws Exception {
		ProxyFactory pf = new ProxyFactory();
		
		pf.setSuperclass(entity.getClass());
		
		Relation rel = new Relation();
		rel.setCanonicalClassName(entity.getClass().getCanonicalName());
		rel.setKeyId(keyId);
		
		ProxyRelation<T> handler = new ProxyRelation<>();
		
		handler.setEntity(entity);
		handler.setRelation(rel);
		
		return (T) pf.create(null, null, handler);
	}
	
	public static <T extends Serializable> T getProxy(T entity) throws Exception {
		return getProxy(entity, (Serializable) IgniteEntityScanner.getInstance().getPrimaryKeyField(entity.getClass()).get(entity));
	}
	
}
