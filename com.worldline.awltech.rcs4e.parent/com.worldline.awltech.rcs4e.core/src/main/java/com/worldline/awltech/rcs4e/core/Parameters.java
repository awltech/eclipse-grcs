package com.worldline.awltech.rcs4e.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Helper class, that wraps parameter management behind a fluent API.
 * 
 * @author mvanbesien
 * 
 */
public final class Parameters {

	/**
	 * Underlying set of parameters
	 */
	private final Set<Parameter> parameters = new HashSet<>();

	/*
	 * Private Constructor
	 */
	private Parameters() {
	}

	/**
	 * Creates a new empty parameters' wrapper
	 * 
	 * @return instance
	 */
	public static Parameters create() {
		return new Parameters();
	}

	/**
	 * Adds a new parameter, identified by its key and value, in this Parameters
	 * group
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Parameters with(String key, Object value) {
		this.parameters.add(new Parameter(key, value));
		return this;
	}

	/**
	 * Returns the underlying set of parameters.
	 * 
	 * @return
	 */
	public Set<Parameter> get() {
		return parameters;
	}

}
