package com.github.vidaniello.ignite.aj;

public aspect Example {
	
	void around() : call(void com.github.vidaniello.ignite.aj.SimplePojo.getAge()) {
		
        System.out.println("Log before...");
        proceed();
        System.out.println("After log...");
        
	}
	
	
	
}