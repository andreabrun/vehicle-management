package com.andreabrun.vehiclemanagement.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class ViewUtils {
	
	private static Map<UserFillable, String> getUserFillableFields(Class<?> clazz) {
		
		Map<UserFillable, String> res = new HashMap<UserFillable, String>();
		
		Field[] fields = clazz.getDeclaredFields();
		
        for(Field f : fields) {
        	UserFillable annotation = f.getAnnotation(UserFillable.class);
        	if(annotation != null) {
        		res.put(annotation, f.getName());
        	}
        }
        
        return res;
	}
	
	private static Object invokeGetter(Object object, String fieldName) {
        try {
            String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getterMethod = object.getClass().getMethod(getterName);
            return getterMethod.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke getter for field '" + fieldName + "': " + e.getMessage(), e);
        }
    }
	
	public static Component createSimpleOutputComponents(String label, String value){		
		Div container = new Div();
    	
		Div labelDiv = new Div(label);
		StyleUtils.applyStyle(labelDiv, StyleUtils.VIEW_UTILS_LABEL_STYLE);
		container.add(labelDiv);
		
		Div valueDiv = new Div(value);
		StyleUtils.applyStyle(valueDiv, StyleUtils.VIEW_UTILS_VALUE_STYLE);
		container.add(valueDiv);
		
		StyleUtils.applyStyle(container, StyleUtils.VIEW_UTILS_CONTAINER_STYLE);
		return container;
	}
	
	public static <T> List<Component> createOutputComponents(Class<T> clazz, Object obj) {
		
		List<Component> res = new ArrayList<Component>();
        
        Map<UserFillable, String> fields = getUserFillableFields(clazz);
        
        List<UserFillable> keys = new ArrayList<UserFillable>();
        keys.addAll(fields.keySet());
        keys.sort(Comparators.USER_FILLABLE_ORDER);

        for(UserFillable key : keys) {
        	
        	String label = key.label();
        	String fieldName = fields.get(key);
        		
        	if(!StringUtils.hasLength(label))
        		label = fieldName;

    		Object result = invokeGetter(obj, fieldName);
    		String value = result != null ? result.toString() : null;
    		
    		Component container = createSimpleOutputComponents(label, value);
    		res.add(container);
        }
        
        return res;
	}
	
}
