package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

/**
 * Scan all 
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
	private Map<Class<?>, Field> primaryKey_fields = new HashMap<>();
	private Map<Class<?>, Set<Field>> entity_fields = new HashMap<>();
	
	private IgniteEntityScanner() {
		
	}
	
	public synchronized void setScanPath(String scanPath) {
		scanPath = this.scanPath;
	}
	
	public synchronized Field getPrimaryKeyField(Class<? extends Serializable> clazz) throws Exception {
		if(!initialized)
			load();
		
		if(loadError)
			throw new Exception("Load error! check exception throwned at first invocation.");
		
		return primaryKey_fields.get(clazz);
	}
	
	
	private void load() {
		try {
			log.trace("Start finding all @"+Entity.class.getCanonicalName()+" annotated classes...");
			
			Reflections rf = null;
			if(scanPath==null)
				rf = new Reflections();
			else {
				log.debug("custom scanPath for reflections: "+scanPath);
				rf = new Reflections(scanPath);
			}
			
			Set<Class<?>> tmp_classEntities = rf.getTypesAnnotatedWith(Entity.class);
			
			log.trace("Finded "+tmp_classEntities.size()+", start checking...");
			
			for(Class<?> classEntity : tmp_classEntities) {
				log.trace("check "+classEntity.getCanonicalName());
				List<Field> allField = FieldUtils.getAllFieldsList(classEntity);
				try {
					//check
					//check(allField);
					
					//at least one and only one @PrimaryKey annotated field
					Field primaryKeyField = null;
					
					for(Field f : allField) {
						
						if(f.isAnnotationPresent(PrimaryKey.class)){
							if(primaryKeyField!=null) {
								log.error(classEntity.getCanonicalName()+" has more than one @PrimaryKey annotated field.");
								loadError=true;
								break;
							}
							primaryKeyField = f;
							continue;
						}
						
					}
					
					if(primaryKeyField==null) {
						log.error(classEntity.getCanonicalName()+" has no @PrimaryKey annotated field.");
						loadError=true;
					}else {
						primaryKey_fields.put(classEntity, primaryKeyField);
						log.trace(classEntity.getCanonicalName()+" has @PrimaryKey annotated field '"+primaryKeyField.getName()+"'");
					}
					
					Set<Field> allEntityField = new HashSet<>();
					
					//no same lower-case fields name
					for(Field fL : allField) {
						
						if(fL.isAnnotationPresent(PrimaryKey.class))
							continue;
						
						boolean same = false;
						for(Field fR : allField) {
							if(fL.getName().toLowerCase().equals(fR.getName())) {
								same=true;
								loadError=true;
								break;
							}
						}
						
						if(!same) {
							allEntityField.add(fL);
							log.trace(classEntity.getCanonicalName()+" has filed"+fL.getName()+"' ok.");
						} else {
							log.error(classEntity.getCanonicalName()+" has field '"+fL.getName()+"' duplicate name.");
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
			log.fatal("Error loading entities config.");
			log.error(e.getMessage(),e);
			loadError=true;
		}
		
	}
}
