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

	private final int WIDTH = 300;
	
	private final VerticalLayout layout = new VerticalLayout();
	private List<TextField> tfs;
	
	public ListStringField() {
		layout.addClassName("list-string-field");
		
		tfs = new ArrayList<TextField>();
		
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth(WIDTH + "px");
		
		Button addValueButton = new Button("+");
		addValueButton.addClickListener(this::addTextFieldClick);
		addValueButton.getStyle().set("width", WIDTH / 2 - 10 + "px");
		headerLayout.add(addValueButton);
		
		Button removeValueButton = new Button("-");
		removeValueButton.getStyle().set("width", WIDTH / 2 - 10 + "px");
		removeValueButton.addClickListener(this::removeTextFieldClick);
		headerLayout.add(removeValueButton);
		
		layout.add(headerLayout);
		
		TextField tf = new TextField();
		tfs.add(tf);
		layout.add(tf);

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
