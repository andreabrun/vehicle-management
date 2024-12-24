package com.andreabrun.vehiclemanagement.view;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VehicleDocumentSummaryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

	public VehicleDocumentSummaryView(VehicleDocument vd, int width) {
		
		super();
		
		String title = vd.getKey();
		add(new H2(title));
		
		String description = vd.getDescription();
		add(new H4(description));
		
		List<String> files = vd.getDocumentFiles();
		for(String f : files) {
			add(new H4(f));
		}
		
		String date = vd.getStringDate();
		add(new H4(date));
		
		String mileage = vd.getMileage().toString(); 
		add(new H4(mileage));
		
		setWidth(width, Unit.PIXELS);
		
		getStyle().set("border-style", "solid");
        
	}
}

