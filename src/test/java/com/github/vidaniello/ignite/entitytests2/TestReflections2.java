package com.github.vidaniello.ignite.entitytests2;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.github.vidaniello.ignite.data.IgniteEntityScanner;
import com.github.vidaniello.ignite.data.relations.RelationProxyFactory;

public class TestReflections2 {

	{
	     //URL file di configurazione Log4j2
	     System.setProperty("log4j.configurationFile", "https://gist.github.com/vidaniello/e6cf911c5a440c05f57b946a15d27822/raw/c919f0b423d4b08ec18419cddc0b9bdb7c2e6599/log4j2-test-ignite.xml");
	     
	     //Tips per java.util.logging
	     System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}
	
	private Logger log = LogManager.getLogger();
	
	@Test
	public void testProxy() {
		try {
			
			SimplePojo sp1 = new SimplePojo();
			sp1.setId(1);
			sp1.setName("first");
			
			SimplePojo sp1Proxed = RelationProxyFactory.getProxy(sp1);
			
			String name = sp1Proxed.getName();
			
			Assert.assertTrue(name.equals(sp1.getName()));
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Test @Ignore
	public void test1() {
		try {
			
			IgniteEntityScanner.getInstance().getPrimaryKeyField(EntityWithFieldSerializable.class);
			
			Map<Class<?>,List<String>> errors = IgniteEntityScanner.getInstance().getAllErrors();
			
			int i = 0;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
