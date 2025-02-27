package com.andreabrun.vehiclemanagement.form;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.form.fields.ListStringField;
import com.andreabrun.vehiclemanagement.form.fields.PeriodField;
import com.andreabrun.vehiclemanagement.utils.Comparators;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

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
	
	@SuppressWarnings("unchecked")
	public static <T> List<Component> createInputComponents(Class<T> clazz, BeanValidationBinder<T> binder, VehicleContainer vc) {
		
		List<Component> res = new ArrayList<Component>();
        
        Map<UserFillable, String> fields = getUserFillableFields(clazz);
        
        List<UserFillable> keys = new ArrayList<UserFillable>();
        keys.addAll(fields.keySet());
        keys.sort(Comparators.USER_FILLABLE_ORDER);

        for(UserFillable key : keys) {
        	
        	String label = key.label();
        	if(StringUtils.isEmpty(label))
        		label = fields.get(key);
        	
        	if(key.type().equals(UserFillable.DATE)) {
        		DatePicker tf = new DatePicker(label);
                binder.bind(tf, fields.get(key));
                res.add(tf);
        	} 
        	else if(key.type().equals(UserFillable.INTEGER)) {
        		IntegerField tf = new IntegerField(label);
        		binder.forField(tf)
        			.bind(fields.get(key));
                res.add(tf);
        	}
        	else if(key.type().equals(UserFillable.DOUBLE)) {
        		TextField tf = new TextField(label);
        		binder.forField(tf)
        			.withConverter(
        					value -> value == null || value.isEmpty() ? 0f : Float.parseFloat(value), 
							value -> value == null ? "" : value.toString()
        			).bind(fields.get(key));
                res.add(tf);
        	}
        	else if(key.type().equals(UserFillable.PERIOD)) {
        		PeriodField periodField = new PeriodField();
                periodField.setLabel("Enter Period");
                binder.forField(periodField).bind(fields.get(key));
                res.add(periodField);
        	}
        	else if(key.type().equals(UserFillable.TEXTLIST)) {
        		ListStringField listStringField = new ListStringField();
        		listStringField.setLabel("List of values");
                binder.forField(listStringField).bind(fields.get(key));
                res.add(listStringField);
        	}
        	else if(key.type().equals(UserFillable.COMBOBOX)) {
        		ComboBox<String> cb = new ComboBox<>();
        		List<String> values = new ArrayList<String>();
        		Method method;
				try {
					method = (VehicleContainer.class).getMethod(key.valueSource());
					values = (List<String>) method.invoke(vc);
				} catch (Exception e) {
					e.printStackTrace();
				}
        		cb.setItems(values);
                binder.forField(cb).bind(fields.get(key));
                res.add(cb);
        	}
        	else {
        		// DEFAULT TEXT
        		TextField tf = new TextField(label);
                binder.bind(tf, fields.get(key));
                res.add(tf);
        	}
        }
        
        return res;
	}
	
}
