package com.github.vidaniello.ignite.data;

public class IgniteEntityManagerFactory {

	public static IgniteEntityManager instance() {
		return new IgniteEntityManagerImpl();
	}
}
