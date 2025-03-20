package com.andreabrun.vehiclemanagement.view;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.andreabrun.vehiclemanagement.utils.ViewUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Anchor;
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
			Anchor pdfLink = ComponentsUtils.getPDFAnchorFromPath(vd.getDocumentsPath() + "/" + f);
	        add(pdfLink);
		}
		
		String date = vd.getStringDate();
		Component simpleDateOutput = ViewUtils.createSimpleOutputComponents("Date", date);
		add(simpleDateOutput);
		
		String mileage = vd.getMileage().toString(); 
		Component simpleMileageOutput = ViewUtils.createSimpleOutputComponents("km", mileage);
		add(simpleMileageOutput);
		
		List<Component> outputComponents = ViewUtils.createOutputComponents(VehicleDocument.class, vd);
		add(outputComponents);
		
		StyleUtils.applyStyle(this, StyleUtils.VEHICLE_DOCUMENT_VIEW_STYLE);
		setWidth(width, Unit.PIXELS);
        
	}
}

