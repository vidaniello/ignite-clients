package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * Scan all annotated @Entity
 * @author Vincenzo D'Aniello (vidaniello@gmail.com) github.com/vidaniello
 *
 */
public class IgniteEntityScanner {

	
	
	private static IgniteEntityScanner igniteEntityScanner = new IgniteEntityScanner();
	public static IgniteEntityScanner getInstance() throws Exception {return igniteEntityScanner;}
	
	
	
	private Logger log = LogManager.getLogger();
	
	private String scanPath;
	private boolean initialized;
	private boolean loadError;
	private Set<Class<?>> classEntities = new HashSet<>();
	private Map<Class<?>,List<String>> classEntities_errors = new HashMap<>();
	private Map<Class<?>, Field> primaryKey_fields = new HashMap<>();
	private Map<Class<?>, Set<Field>> entity_fields = new HashMap<>();
	
	private IgniteEntityScanner() {
		
	}
	
	/**
	 * Set the package filter, start with <i>package name</i>.<br>
	 * Used for tests.
	 * @param scanPath the package name
	 */
	public synchronized void setScanPath(String scanPath) {
		this.scanPath = scanPath;
	}
	
	public synchronized Field getPrimaryKeyField(Class<? extends Serializable> clazz) throws Exception {
		if(!initialized)
			load();
		
		if(loadError)
			throw new Exception("Load error! check exception throwned at first invocation.");
		
		return primaryKey_fields.get(clazz);
	}
	
	public synchronized Map<Class<?>,List<String>> getAllErrors() throws Exception {
		if(!initialized)
			load();
		return classEntities_errors;
	}
	
	
	private void load() {
		try {
			log.trace("Start finding all @"+Entity.class.getCanonicalName()+" annotated classes...");
			
			Set<Class<?>> tmp_classEntities = null;
			if(scanPath==null) {
				tmp_classEntities = new Reflections().getTypesAnnotatedWith(Entity.class);
			}else {
				
				log.debug("custom package scanPath for reflections start with: "+scanPath);
				
				tmp_classEntities = new Reflections()
						.getTypesAnnotatedWith(Entity.class)
						.stream()
						.filter(clz->clz.getPackage().getName().startsWith(scanPath))
						.collect(Collectors.toSet());
			}
			
			
			log.trace("Finded "+tmp_classEntities.size()+ " annotated @Entity, start checking...");
			
			
			for(Class<?> classEntity : tmp_classEntities) {
				log.trace("check "+classEntity.getCanonicalName());
				
				List<String> errors = new ArrayList<>();
				classEntities_errors.put(classEntity, errors);
				
				//Check implement Serializable
				if(!Serializable.class.isAssignableFrom(classEntity)){
					loadError=true;
					log.error(classEntity.getCanonicalName()+" not implement Serializable interface.");
					errors.add("not serializable.");
				}
				
				
 				List<Field> allField = FieldUtils.getAllFieldsList(classEntity)
 						.stream()
 						.filter(field->!Modifier.isStatic(field.getModifiers()))
 						.collect(Collectors.toList());
				try {
					//check
					//check(allField);
					
					//at least one and only one @PrimaryKey annotated field
					Field primaryKeyField = null;
					boolean twoPrimaryKey=false;
					for(Field f : allField) {
						
						if(f.isAnnotationPresent(PrimaryKey.class)){
							if(primaryKeyField!=null) {
								loadError=true;
								twoPrimaryKey=true;
								log.error(classEntity.getCanonicalName()+" has more than one @PrimaryKey annotated field.");
								errors.add("more than one @PrimaryKey annotated field.");
								break;
							}
							primaryKeyField = f;
							//continue;
						}
						
					}
					
					if(primaryKeyField==null) {
						loadError=true;
						log.error(classEntity.getCanonicalName()+" has no @PrimaryKey annotated field.");
						errors.add("no @PrimaryKey annotated field.");
					}else if(!twoPrimaryKey){
						if(Serializable.class.isAssignableFrom(primaryKeyField.getType())) {
							primaryKey_fields.put(classEntity, primaryKeyField);
							log.trace(classEntity.getCanonicalName()+" has @PrimaryKey annotated field '"+primaryKeyField.getName()+"' of type "+primaryKeyField.getType().getCanonicalName());
						}else {
							loadError=true;
							log.error(classEntity.getCanonicalName()+" has @PrimaryKey annotated field who not implements Serializable.");
							errors.add("@PrimaryKey annotated field not implements Serializable.");
						}
					}
					
					Set<Field> allEntityField = new HashSet<>();
					
					//no same lower-case fields name
					for(Field fL : allField) {
						
						if(fL.isAnnotationPresent(PrimaryKey.class))
							continue;
						
						boolean same = false;
						for(Field fR : allField) {
							if(fL.equals(fR))continue;
							if(fL.getName().toLowerCase().equals(fR.getName().toLowerCase())) {
								same=true;
								loadError=true;
								break;
							}
						}
						
						if(!same) {
							if(Serializable.class.isAssignableFrom(fL.getType())) {
								allEntityField.add(fL);
								log.trace(classEntity.getCanonicalName()+" has field '"+fL.getName()+"' of type "+fL.getType().getCanonicalName()+" ok.");
							}else {
								loadError=true;
								log.error(classEntity.getCanonicalName()+" has a field who not implements Serializable.");
								errors.add("field who not implements Serializable.");
							}
						} else {
							log.error(classEntity.getCanonicalName()+" has field '"+fL.getName()+"' duplicate name.");
							errors.add("field "+fL.getName()+"' duplicate name.");
						}
					}
						
					if(!loadError) 
						entity_fields.put(classEntity, allEntityField);
					
				}catch(Exception e) {
					log.error(e.getMessage(),e);
					loadError=true;
				}
				
				if(!loadError) {
					classEntities.add(classEntity);
					log.trace(classEntity.getCanonicalName()+" check done! all ok.");
				}
			}
			
			if(loadError)
				throw new Exception("Checking entities got error!");
			
		}catch(Exception e) {
			log.fatal("Error loading entities configuration.");
			log.error(e.getMessage(),e);
			loadError=true;
		}
		
	}
	
	private void checkOfFields() {
		
	}
}
