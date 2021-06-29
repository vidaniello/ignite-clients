package com.github.vidaniello.ignite.data;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

public class IgniteEntityProxy implements MethodHandler, MethodFilter{

	public static <T extends IgniteEntity<?>> T newInstance(Class<T> clazz) throws Exception {
		ProxyFactory pf = new ProxyFactory();
		pf.setSuperclass(clazz);
		IgniteEntityProxy hnd = new IgniteEntityProxy();
		pf.setFilter(hnd);
		
		//Class<?> cls = pf.createClass();
		return clazz.cast(pf.create(null, null, hnd));
	}

	private IgniteEntityProxy() {
		
	}
	
	@Override
	public boolean isHandled(Method m) {
		boolean ret = m.getDeclaringClass().equals(Object.class) ? false : true;
		System.out.println(m.getDeclaringClass().getCanonicalName()+" "+m.getName()+" "+ret);
		return ret;
	}

	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		
		return "";
	}
}
