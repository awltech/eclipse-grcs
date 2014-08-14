package com.worldline.awltech.rcs4e.core;

/**
 * Injectable Parameter implementation
 * 
 * @author mvanbesien
 * 
 */
public class Parameter {

	/**
	 * Parameter Key
	 */
	private String key;

	/**
	 * Parameter Value
	 */
	private Object value;

	/**
	 * Creates a new injectable parameter instance
	 * 
	 * @param key
	 * @param value
	 */
	public Parameter(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return Parameter's Key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return Parameter's Value
	 */
	public Object getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Parameter other = (Parameter) obj;
		return this.key != null ? this.key.equals(other.key) : other.key == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.key != null ? this.key.hashCode() : 0;
	}
	
}
