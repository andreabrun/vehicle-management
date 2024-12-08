package com.andreabrun.vehiclemanagement.form;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.utils.Comparators;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import io.micrometer.common.util.StringUtils;

public class InputFormUtils {
	
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
	
	public static <T> List<Component> createInputComponents(Class<T> clazz, BeanValidationBinder<T> binder) {
		
		List<Component> res = new ArrayList<Component>();
        
        Map<UserFillable, String> fields = getUserFillableFields(clazz);
        
        List<UserFillable> keys = new ArrayList<UserFillable>();
        keys.addAll(fields.keySet());
        keys.sort(Comparators.USER_FILLABLE_ORDER);

        for(UserFillable key : keys) {
        	
        	String label = key.label();
        	if(StringUtils.isEmpty(label))
        		label =  fields.get(key);
        	
        	TextField tf = new TextField(label);
            binder.bind(tf, fields.get(key));
            res.add(tf);
        }
        
        return res;
	}
	
}
