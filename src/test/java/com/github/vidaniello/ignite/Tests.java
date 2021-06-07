package com.github.vidaniello.ignite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Tests {

	
	static{

	     //URL file di configurazione Log4j2
	     System.setProperty("log4j.configurationFile", "http://address.tld/log4j2.xml");

	     //Tips per java.util.logging
	     System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}
	
	private Logger log = LogManager.getLogger();
	
	@Test
	public void tesOne() {
		try {
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
}
