package com.andreabrun.vehiclemanagement.form;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import io.micrometer.common.util.StringUtils;

public class InputFormUtils {
	
	public static Map<String, String> getUserFillableFields(Class<?> clazz) {
		
		Map<String, String> res = new HashMap<String, String>();
		
		Field[] fields = clazz.getDeclaredFields();
		
        for(Field f : fields) {
        	UserFillable annotation = f.getAnnotation(UserFillable.class);
        	if(annotation != null) {
        		
        		String name = f.getName();
        		String label = annotation.label();
        		
        		if(StringUtils.isEmpty(label)) {
        			label = name;
        		}
        		res.put(name, label);
        	}
        }
        
        return res;
	}
	
	public static <T> List<Component> createInputComponents(Class<T> clazz, BeanValidationBinder<T> binder) {
		
		List<Component> res = new ArrayList<Component>();
        
        Map<String, String> fields = getUserFillableFields(clazz);

        for(String key : fields.keySet()) {
        	TextField tf = new TextField(fields.get(key));
            binder.bind(tf, key);
            res.add(tf);
        }
        
        return res;
	}
	
}
