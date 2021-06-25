package com.github.vidaniello.ignite.aj;

import org.junit.Test;

public class TestAj {

	@Test
	public void testAj1() {
		try {
			SimplePojo sp = new SimplePojo();
			
			sp.setAge(23);
			
			sp.getAge();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
