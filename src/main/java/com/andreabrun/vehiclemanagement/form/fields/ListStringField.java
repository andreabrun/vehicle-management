package com.andreabrun.vehiclemanagement.form.fields;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ListStringField extends CustomField<List<String>>{

	private static final long serialVersionUID = 1L;
	
	private final VerticalLayout layout = new VerticalLayout();
	private List<TextField> tfs;
	
	public ListStringField() {
		
		layout.addClassName("list-string-field");
		
		tfs = new ArrayList<TextField>();
		
		layout.getStyle().set("padding-left", "0").set("padding-right", "0");
		
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.getStyle().set("width", "100%");
		
		Button addValueButton = new Button("+");
		addValueButton.addClickListener(this::addTextFieldClick);
		addValueButton.getStyle().set("width", "40%");
		headerLayout.add(addValueButton);
		
		Button removeValueButton = new Button("-");
		removeValueButton.getStyle().set("width", "40%");
		removeValueButton.addClickListener(this::removeTextFieldClick);
		headerLayout.add(removeValueButton);
		
		layout.add(headerLayout);
		
		addTextField();

        add(layout);
	}
	
	private void addTextField() {
		addTextField(null);
	}
	
	private void removeTextField() {
		int size = tfs.size();
		if(size > 1) {
			TextField tf = tfs.remove(size - 1);
			layout.remove(tf);
		}
	}
	
	private void addTextField(String s) {
		TextField tf = new TextField();
		if(s != null)
			tf.setValue(s);
		tf.getStyle().set("width", "100%");
		tfs.add(tf);
		layout.add(tf);
	}
	
	private void addTextFieldClick(ClickEvent<?> e) {
		addTextField();
	}
	
	private void removeTextFieldClick(ClickEvent<?> e) {
		removeTextField();
	}
	
	@Override
	protected List<String> generateModelValue() {
		List<String> res = new ArrayList<String>();
		for(TextField tf : tfs) {
			String s = tf.getValue();
			res.add(s);
		}
		return res;
	}

	@Override
	protected void setPresentationValue(List<String> newPresentationValue) {
		List<String> values = newPresentationValue != null ? newPresentationValue : new ArrayList<String>();
		tfs = new ArrayList<TextField>();
		for(String s : values) {
			addTextField(s);
		}
	}

}
