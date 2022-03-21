package com.github.vidaniello.ignite.data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
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
	//private boolean initialized;
	private boolean loadError;
	private Set<String> packageScanned = new HashSet<>();
	private Set<Class<?>> classEntities = new HashSet<>();
	private Map<Class<?>,List<String>> classEntities_errors = new HashMap<>();
	
	private Map<Class<?>,Field> primaryKey_fields = new HashMap<>();
	private Map<Class<?>,Set<Field>> entity_fields = new HashMap<>();
	
	private Map<Class<?>,Set<Field>> entity_fields_relations = new HashMap<>();
	
	//private Map<Class<?>,Boolean> checked_notPrimitiveFields = new HashMap<>();
	
	private IgniteEntityScanner() {
		
	}
	
	/**
	 * Set the package filter, start with <i>package name</i>.<br>
	 * Used for tests.
	 * @param scanPath the package name
	 */
	private boolean setScanPath(String scanPath) {
		this.scanPath = scanPath;
		return packageScanned.add(scanPath);
	}
	
	public synchronized Field getPrimaryKeyField(Class<? extends Serializable> clazz) throws Exception {
		//if(!initialized)
			//load();

		if(setScanPath(clazz.getPackage().getName())) {
			load();
		}
		
		if(loadError)
			throw new Exception("Load error! check exceptions throwned.");
		
		return primaryKey_fields.get(clazz);
	}
	
	/**
	 * Find all entities errors.
	 * @return
	 * @throws Exception
	 */
	public synchronized Map<Class<?>,List<String>> getAllErrors() throws Exception {
		//if(!initialized)
			//load();
		return classEntities_errors;
	}
	
	
	private synchronized void load() {
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
 						.filter(field->{
 							return !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())
 									&& !field.isAnnotationPresent(Transient.class);
 						})
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
						//if(Serializable.class.isAssignableFrom(primaryKeyField.getType())) {
							
						//walk into primaryKey
						if(checkOfField(primaryKeyField)) {
							primaryKeyField.setAccessible(true);
							primaryKey_fields.put(classEntity, primaryKeyField);
							//log.trace(classEntity.getCanonicalName()+" has @PrimaryKey annotated field '"+primaryKeyField.getName()+"' of type "+primaryKeyField.getType().getCanonicalName());
						} else {
							loadError=true;
							log.error(classEntity.getCanonicalName()+" has @PrimaryKey annotated field check failed.");
							errors.add("@PrimaryKey annotated field check failed.");
						}
						
							/*
						}else {
							loadError=true;
							log.error(classEntity.getCanonicalName()+" has @PrimaryKey annotated field who not implements Serializable.");
							errors.add("@PrimaryKey annotated field not implements Serializable.");
						}
						*/
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
							
							//walk into field
							if(checkOfField(fL)) {
								fL.setAccessible(true);
								allEntityField.add(fL);
								//log.trace(classEntity.getCanonicalName()+" has field '"+fL.getName()+"' of type "+fL.getType().getCanonicalName()+" ok.");
							}else {
								loadError=true;
								log.error(classEntity.getCanonicalName()+" has field '"+fL.getName()+"' check errors.");
								errors.add("field "+fL.getName()+"' check errors.");
							}
						
						} else {
							loadError=true;
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
	
	private boolean checkOfField(Field fToCheck) throws ClassNotFoundException {
		
		Class<?> type = fToCheck.getType();
		
		if(ClassUtils.isPrimitiveOrWrapper(type)) {
			log.trace("field "+fToCheck.getName()+" is primitive or wrapper, of type "+type.getCanonicalName());
			return true;
		}else if(type.isArray() && ClassUtils.isPrimitiveOrWrapper(type.getComponentType())) {
			log.trace("field "+fToCheck.getName()+" is primitive or wrapper array, of type "+type.getComponentType().getCanonicalName());
			return true;
		}else if(type.isArray()) {
			return checkArray(fToCheck, type.getComponentType());
		}else if(Iterable.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
			return checkParameterizedTypes(fToCheck, type);
		}else if(Serializable.class.isAssignableFrom(type)) {
			log.trace("field "+fToCheck.getName()+" is Serializable, of type "+type.getCanonicalName());
			return true;
		}
		return false;
	}
	
	private boolean checkParameterizedTypes(Field fToCheck, Class<?> type) throws ClassNotFoundException {
		ParameterizedType pt = ParameterizedType.class.cast(fToCheck.getGenericType());
		Type[] arrType = pt.getActualTypeArguments();
		
		String str = pt.getRawType().getTypeName()+"<";
		
		int nRip = 1;
		boolean exit = true;
		while(exit) {
			if(ParameterizedType.class.isAssignableFrom(arrType[0].getClass())) {
				pt = ParameterizedType.class.cast(arrType[0]);
				arrType = pt.getActualTypeArguments();
				str +=  pt.getRawType().getTypeName()+"<";
				nRip++;
			}else
				exit=false;
		}
		
		
		if(Iterable.class.isAssignableFrom(type)) {
			Class<?> valueClass = Class.forName(arrType[0].getTypeName());
			
			if(Iterable.class.isAssignableFrom(valueClass) || Map.class.isAssignableFrom(valueClass)){
				return checkParameterizedTypes(fToCheck, valueClass);
			}else {
				log.trace("field "+fToCheck.getName()+" is of type "+str+valueClass.getCanonicalName()+StringUtils.repeat(">", nRip));
				return true;
			}
		}else if(Map.class.isAssignableFrom(type)) {
			Class<?> keyClass = Class.forName(arrType[0].getTypeName());
			Class<?> valueClass = Class.forName(arrType[1].getTypeName());                                                                                                                                            
			log.trace("field "+fToCheck.getName()+" is of type "+type.getCanonicalName()+"<"+keyClass.getCanonicalName()+","+valueClass.getCanonicalName()+">");
			return true;
		}
		
		return false;
	}

	private boolean checkArray(Field fToCheck, Class<?> type) throws ClassNotFoundException {
		
		if(Iterable.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
			//return checkParameterizedTypes(fToCheck, type);
			return false;
		}else if(Serializable.class.isAssignableFrom(type)) {
			log.trace("field "+fToCheck.getName()+" is Serializable array, of type "+type.getCanonicalName());
			return true;
		}
		
		return false;
	}
}
