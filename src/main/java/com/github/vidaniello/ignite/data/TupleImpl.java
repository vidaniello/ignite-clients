package com.github.vidaniello.ignite.data;

import java.io.Serializable;

public class TupleImpl<TYPE extends Serializable, VALUE extends Serializable> implements Tuple<TYPE, VALUE> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _canonicalClassName;
	private VALUE _value;
	
	@Override
	public String getType() {
		return _canonicalClassName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Class<TYPE> getClassType() throws ClassNotFoundException {
		return (Class<TYPE>) Class.forName(_canonicalClassName);
	}

	@Override
	public VALUE getValue() {
		return _value;
	}

}
