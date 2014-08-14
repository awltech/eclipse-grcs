package com.worldline.awltech.rcs4e.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Parameterized Annotation.
 * 
 * This annotation can be put on Callable fields, to be injected at runtime
 * 
 * @author mvanbesien
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Parameterized {

	/**
	 * @return key of the parameter
	 */
	String value();

}
