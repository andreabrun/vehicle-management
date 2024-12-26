package com.andreabrun.vehiclemanagement.entities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UserFillable {
	public static final String TEXT = "text";
	public static final String DATE = "date";
	public static final String INTEGER = "int";
	public static final String DOUBLE = "double";
	public static final String TEXT_LIST = "text-list";
	public static final String PERIOD = "period";
	public static final String COMBOBOX = "combobox";
	public static final String TEXTLIST = "textlist";
	
	String label() default "";
	int order() default 0;
	String type() default TEXT;
	
	/**
	 * Name of VehicleContainer function that returns the list of values of the combobox.
	 * The function will be called via Reflection by the function that generate the input components
	 * @return
	 */
	String valueSource() default "";
}
