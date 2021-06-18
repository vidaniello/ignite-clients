package com.github.vidaniello.ignite;

import java.util.Map;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.binary.BinaryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Tests {

	
	static{

	     //URL file di configurazione Log4j2
	     System.setProperty("log4j.configurationFile", "https://gist.github.com/vidaniello/e6cf911c5a440c05f57b946a15d27822/raw/c919f0b423d4b08ec18419cddc0b9bdb7c2e6599/log4j2-test-ignite.xml");

	     //Tips per java.util.logging
	     System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}
	
	private Logger log = LogManager.getLogger();
	
	

	
	/**
	 * Make sure you have a server node started as default before starting this test.
	 */
	@Test //@Ignore
	public void firstFindEntities() {
		try {		
			
			
			

			
			int stop = 0;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Make sure you have a server node started as default before starting this test.
	 */
	@Test @Ignore
	public void tesClientLocalTestnet() {
		try {
			
			Ignite testnet = ClientProvider.instance().ignite();
			
			Ignite testnetSame = ClientProvider.instance().ignite();
			
			int i = 0;
			//testnet.close();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	
	@Test
	public void tesLoadingConfig() {
		try {
			Map<String,String> testnet =  ClientProvider.instance().getMapFromPropertiesByClusterTag("testnet");
			Map<String,String> mainnet =  ClientProvider.instance().getMapFromPropertiesByClusterTag("mainnet");
			Map<String,String> other =  ClientProvider.instance().getMapFromPropertiesByClusterTag("other");
			Map<String,String> empty =  ClientProvider.instance().getMapFromPropertiesByClusterTag("empty");
			
			Assert.assertTrue(testnet.get("clusterTag").equals("testnet"));
			Assert.assertTrue(!testnet.get("uri").isEmpty());
			
			Assert.assertTrue(mainnet.get("clusterTag").equals("mainnet"));
			Assert.assertTrue(!mainnet.get("uri").isEmpty());
			
			Assert.assertTrue(other.get("clusterTag").equals("other"));
			Assert.assertTrue(!other.get("uri").isEmpty());
			
			Assert.assertTrue(empty.isEmpty());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
}
