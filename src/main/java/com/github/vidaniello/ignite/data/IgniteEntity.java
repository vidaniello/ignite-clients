package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyObject;

/**
 * Base interface for all entities.
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
public interface IgniteEntity<PRIMARY_KEY extends Serializable> extends Serializable {

	public PRIMARY_KEY getId();
	
}
