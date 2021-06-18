package com.github.vidaniello.ignite.data.relations;

import java.io.Serializable;

public class Relation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String canonicalClassName;
	private Serializable keyId;
	
	public Relation() {
		
	}

	public Relation(String canonicalClassName, Serializable keyId) {
		super();
		this.canonicalClassName = canonicalClassName;
		this.keyId = keyId;
	}

	public String getCanonicalClassName() {
		return canonicalClassName;
	}

	public void setCanonicalClassName(String canonicalClassName) {
		this.canonicalClassName = canonicalClassName;
	}

	public Serializable getKeyId() {
		return keyId;
	}

	public void setKeyId(Serializable keyId) {
		this.keyId = keyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canonicalClassName == null) ? 0 : canonicalClassName.hashCode());
		result = prime * result + ((keyId == null) ? 0 : keyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relation other = (Relation) obj;
		if (canonicalClassName == null) {
			if (other.canonicalClassName != null)
				return false;
		} else if (!canonicalClassName.equals(other.canonicalClassName))
			return false;
		if (keyId == null) {
			if (other.keyId != null)
				return false;
		} else if (!keyId.equals(other.keyId))
			return false;
		return true;
	}


	
	
	
}
