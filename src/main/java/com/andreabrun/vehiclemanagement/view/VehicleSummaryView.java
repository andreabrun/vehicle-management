package com.andreabrun.vehiclemanagement.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import io.micrometer.common.util.StringUtils;

public class VehicleSummaryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    private static final String defaultCoverImagePath = "./images/altcaricon.webp";

	public VehicleSummaryView(VehicleContainer vc, int width) {
		super();
		
		Vehicle vehicle = vc.getVehicle();
		
		String name = vehicle.getName();
		add(new H2(name));

		String coverImagePath = vc.getCoverImagePath();
		Image coverImage = null;
		if(StringUtils.isNotEmpty(coverImagePath)) {
			coverImage = new Image(coverImagePath, "Cover image");
		} else {
			coverImage = new Image(defaultCoverImagePath, "Cover image");
		}
		add(coverImage);
		
		add(new H4("185000 km"));
		add(new H4("Ultimo tagliando: 04/2024 - 175000 km"));
		add(new H4("Scadenza revisione: 05/2025"));
		add(new H4("Scadenza assicurazione: 15/07/2025"));
		setWidth(width, Unit.PIXELS);
        
	}
}