package com.github.vidaniello.ignite.entitytests2;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.github.vidaniello.ignite.data.Entity;
import com.github.vidaniello.ignite.data.PrimaryKey;

@Entity
public class EntityWithFieldSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private PrimaryKeyWithFieldsSerializable id;
	
	private int anInt;
	private Integer anIntWrapped;
	private String aString;
	private FieldSerializable field1;
	
	private int[] anArrayOfInt;
	private Integer[] anArrayOfIntWrapped;
	private String[] anArrayOfString;
	private FieldSerializable[] anArrayOfFieldSerializable;
	
	private Collection<Integer> aCollectionOfIntWrapped;
	private Collection<String> aCollectionOfString;
	private Collection<FieldSerializable> aCollectionOfFieldSerializable;
	
	private Map<Integer, Integer> aMapOfIntInt;
	
	public EntityWithFieldSerializable() {
		
	}
	
	public PrimaryKeyWithFieldsSerializable getId() {
		return id;
	}
	
	public void setId(PrimaryKeyWithFieldsSerializable id) {
		this.id = id;
	}
	
	public FieldSerializable getField1() {
		return field1;
	}
	
	public void setField1(FieldSerializable field1) {
		this.field1 = field1;
	}

	public int getAnInt() {
		return anInt;
	}

	public void setAnInt(int anInt) {
		this.anInt = anInt;
	}

	public Integer getAnIntWrapped() {
		return anIntWrapped;
	}

	public void setAnIntWrapped(Integer anIntWrapped) {
		this.anIntWrapped = anIntWrapped;
	}

	public String getaString() {
		return aString;
	}

	public void setaString(String aString) {
		this.aString = aString;
	}

	public int[] getAnArrayOfInt() {
		return anArrayOfInt;
	}

	public void setAnArrayOfInt(int[] anArrayOfInt) {
		this.anArrayOfInt = anArrayOfInt;
	}

	public Integer[] getAnArrayOfIntWrapped() {
		return anArrayOfIntWrapped;
	}

	public void setAnArrayOfIntWrapped(Integer[] anArrayOfIntWrapped) {
		this.anArrayOfIntWrapped = anArrayOfIntWrapped;
	}

	public String[] getAnArrayOfString() {
		return anArrayOfString;
	}

	public void setAnArrayOfString(String[] anArrayOfString) {
		this.anArrayOfString = anArrayOfString;
	}

	public FieldSerializable[] getAnArrayOfFieldSerializable() {
		return anArrayOfFieldSerializable;
	}

	public void setAnArrayOfFieldSerializable(FieldSerializable[] anArrayOfFieldSerializable) {
		this.anArrayOfFieldSerializable = anArrayOfFieldSerializable;
	}

	public Collection<Integer> getaCollectionOfIntWrapped() {
		return aCollectionOfIntWrapped;
	}

	public void setaCollectionOfIntWrapped(Collection<Integer> aCollectionOfIntWrapped) {
		this.aCollectionOfIntWrapped = aCollectionOfIntWrapped;
	}

	public Collection<String> getaCollectionOfString() {
		return aCollectionOfString;
	}

	public void setaCollectionOfString(Collection<String> aCollectionOfString) {
		this.aCollectionOfString = aCollectionOfString;
	}

	public Collection<FieldSerializable> getaCollectionOfFieldSerializable() {
		return aCollectionOfFieldSerializable;
	}

	public void setaCollectionOfFieldSerializable(Collection<FieldSerializable> aCollectionOfFieldSerializable) {
		this.aCollectionOfFieldSerializable = aCollectionOfFieldSerializable;
	}

	
}
