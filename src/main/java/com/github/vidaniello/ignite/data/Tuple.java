package com.github.vidaniello.ignite.data;

import java.io.Serializable;

public interface Tuple<TYPE extends Serializable, VALUE extends Serializable> extends Serializable {

	/**
	 * Return canonical class name of stored value
	 * @return
	 */
	public String getType();
	
	public Class<TYPE> getClassType() throws ClassNotFoundException;
	
	public VALUE getValue();
}
