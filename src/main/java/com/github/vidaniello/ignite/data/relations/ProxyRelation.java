package com.github.vidaniello.ignite.data.relations;

import java.io.Serializable;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

public class ProxyRelation<T extends Serializable> implements MethodHandler{

	private T entity;
	private Relation relation;
	
	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		return thisMethod.invoke(entity, args);
	}
	
	public T getEntity() {
		return entity;
	}
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public Relation getRelation() {
		return relation;
	}
	
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
}
