package com.andreabrun.vehiclemanagement.view;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.andreabrun.vehiclemanagement.utils.StyleUtils;
import com.andreabrun.vehiclemanagement.utils.ViewUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VehicleSummaryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 400;
    
    private static final String DEFAULT_COVERIMAGE_NAME = "altcaricon.webp";

	public VehicleSummaryView(VehicleContainer vc) {
		
		super();
		
		Vehicle vehicle = vc.getVehicle();
		
		Div coverImageDiv = new Div();
		
		Image coverImage = null;
		if(vc.isCoverImagePresent()) {
			String coverImagePath = vc.getCoverImagePath();
			coverImage = ComponentsUtils.getImageFromPath(coverImagePath);
		} else {
			coverImage = ComponentsUtils.getImageFromPath(PersistenceUtils.ASSETS_PATH + DEFAULT_COVERIMAGE_NAME);
		}
		coverImage.setWidthFull();
		coverImageDiv.add(coverImage);
		coverImageDiv.getStyle().set("overflow", "hidden").set("align-items", "center").set("vertical-align", "center");
		coverImageDiv.getStyle().set("border-radius", "15px");
		coverImageDiv.setWidth(WIDTH - 20 + "px");
		
		add(coverImageDiv);
		
		String mileageContent = "km: ";
		
		VehicleDocument latestDocument = vc.getLatestVehicleDocument();
		if(latestDocument != null) {
			String mileage = latestDocument.getMileage().toString();
			mileageContent += mileage;
			add(new H4(mileageContent));
		}
		
		List<Component> outputComponents = ViewUtils.createOutputComponents(Vehicle.class, vehicle);
		add(outputComponents);
		
		setWidth(WIDTH, Unit.PIXELS);
        StyleUtils.applyStyle(this, StyleUtils.VEHICLE_SUMMARY_VIEW_STYLE);
        
	}
}