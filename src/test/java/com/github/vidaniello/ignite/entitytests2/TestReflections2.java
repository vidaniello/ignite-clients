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
	
	/*
	@Test
	public void testsolution() {
		String sol1 = solution("Mon", 1);
		Assert.assertTrue(sol1.equals("Tue"));
		
		sol1 = solution("Mon", 2);
		Assert.assertTrue(sol1.equals("Wed"));
		
		sol1 = solution("Mon", 0);
		Assert.assertTrue(sol1.equals("Mon"));
		
		sol1 = solution("Mon", 6);
		Assert.assertTrue(sol1.equals("Sun"));
		
		sol1 = solution("Mon", 7);
		Assert.assertTrue(sol1.equals("Mon"));
		
		sol1 = solution("Mon", 13);
		Assert.assertTrue(sol1.equals("Sun"));
		
		sol1 = solution("Mon", 14);
		Assert.assertTrue(sol1.equals("Mon"));
		
		sol1 = solution("Sun", 0);
		Assert.assertTrue(sol1.equals("Sun"));
		
		sol1 = solution("Sun", 1);
		Assert.assertTrue(sol1.equals("Mon"));
		
		sol1 = solution("Sun", 2);
		Assert.assertTrue(sol1.equals("Tue"));
		
		sol1 = solution("Thu", 3);
		Assert.assertTrue(sol1.equals("Sun"));
		
		sol1 = solution("Thu", 4);
		Assert.assertTrue(sol1.equals("Mon"));
	}
	
	
	public String solution(String S, int K) {
        if(K<0 || K>500)
            throw new RuntimeException("K must be between 0 and 500, "+K+" is a not valid K!");

        String[] weekDays = new String[]{"Mon","Tue","Wed","Thu","Fri","Sat","Sun"}; 

        boolean finded = false;
        int pos = 0;

        for(int i=0;i<weekDays.length;i++)
            if(weekDays[i].equals(S)) {
            	finded=true;
            	pos=i;
            	break;
            }
        
        if(!finded)
        	throw new RuntimeException("day of week "+S+" not finded!");
        
        String ret = S;
        
        if(K>0) {
        	int loop = pos;
        	int i = 0;
        	do {
        		if((loop+1)>6)
        			loop=0;
        		else
        			loop++;
        		i++;
        		
        	}while(i<K);
        	ret = weekDays[loop];
        }
        
        return ret;
    }
	*/
}
