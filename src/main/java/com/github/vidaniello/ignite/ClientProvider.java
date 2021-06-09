package com.github.vidaniello.ignite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

public final class ClientProvider {

	private static String property_filename = "igniteClient.properties";
	private static String systemProperty_property_filename = "IGNITE_CLIENT_PROPERTY_FILE";
	
	private static enum Type{client, configuration}
	
	private static ClientProvider instance = new ClientProvider();
	public static ClientProvider instance() {
		return instance;
	}
	
	
	private URL springConfigurationURL;
	
	private IgniteConfiguration conf;
	
	
	private Map<String,Map<Type,Object>> local_pool;
	
	private ClientProvider() {
		loadProperties();
	}
	
	/**
	 * Get Ignite client by clusterTag
	 * @param clusterTag Case sensitive
	 * @return
	 */
	public synchronized Ignite ignite(String clusterTag) {
		
		return null;
	}
	
	/**
	 * 
	 * @param clusterTag Case sensitive
	 * @return
	 */
	public synchronized Map<String,String> getMapFromPropertiesByClusterTag(String clusterTag){
		Map<String,String> ret = new HashMap<>();
		
		if(clusterTag==null)return ret;
		if(clusterTag.isEmpty()) return ret;
		
		//First find identation
		String identation = null;
		Optional<String> clusterTagOpt = ignite_clients_node_properties.stringPropertyNames()
			.stream()
			.filter(key->key.endsWith("clusterTag"))
			.filter(key->ignite_clients_node_properties.getProperty(key).equals(clusterTag))
			.findFirst();
		
		if(clusterTagOpt.isPresent()) {
			identation = clusterTagOpt.get().substring(0, clusterTagOpt.get().indexOf("."));
			ret = getMapFromPropertiesByIdentation(identation);
		}
		
		return ret;
	}
	
	private Map<String,String> getMapFromPropertiesByIdentation(String identation){
		Map<String,String> ret = new HashMap<>();
		
		for(String key : ignite_clients_node_properties.stringPropertyNames()) 
			if(key.startsWith(identation+"."))
				ret.put(key.substring(identation.length()+1, key.length()), ignite_clients_node_properties.getProperty(key).trim());
		
		
		return ret;
	}
	
	private Properties ignite_clients_node_properties = new Properties();
	/**
	 * Load ignite clients property
	 */
	private void loadProperties() {
		InputStream is = null;
		try {
			is = getClass().getClassLoader().getResourceAsStream(property_filename);
			if(is==null) {
				
				if(System.getProperties().containsKey(systemProperty_property_filename))
					try {
						URL systemProperty_property_filename_url = new URL(System.getProperties().getProperty(systemProperty_property_filename));
						is = systemProperty_property_filename_url.openStream();
					}catch(MalformedURLException mue) {
						File file = new File(System.getProperties().getProperty(systemProperty_property_filename));
						if(file.exists()) 
							is = new FileInputStream(file);
					}
				
			}
			
			if(is!=null)
				ignite_clients_node_properties.load(is);
			
		}catch(IOException e) {
			
		}finally {
			if(is!=null)
				try {is.close();} catch (IOException e) {}
		}
		
	}
}
