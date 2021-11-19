package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

	private PRIMARY_KEY _key_;
	
	private Map<Serializable,Tuple<String,Serializable>> _data_;
	
	private synchronized Map<Serializable,Tuple<String,Serializable>> _getData(){
		if(_data_==null)
			_data_ = new HashMap<>();
		return _data_;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized <T extends Serializable> T get(String fieldName) throws Exception {
		return (T) _getData().get(fieldName).getValue();
	}
	
	@Override
	public synchronized <T extends IgniteEntity<PRIMARY_KEY>> T put(String fieldName, Serializable value) throws Exception {
		return null;
	}
	
	
	@Override
	public final PRIMARY_KEY getId() {
		return _key_;
	}
	
	

}
