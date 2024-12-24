package com.andreabrun.vehiclemanagement.view;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.utils.ComponentsUtils;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VehicleSummaryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
    
    private static final String DEFAULT_COVERIMAGE_NAME = "altcaricon.webp";

	public VehicleSummaryView(VehicleContainer vc) {
		
		super();
		
		Vehicle vehicle = vc.getVehicle();
		
		String name = vehicle.getName();
		H2 title = new H2(name);
		title.getStyle().set("height", "40px").set("position", "sticky").set("top", "0").set("text-align", "center");
		title.setWidthFull();
		// LAYOUT DEBUG
		title.getStyle().set("border", "solid");
		add(title);
		
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
		coverImageDiv.getStyle().set("overflow", "hidden").set("align-items", "center").set("margin", "5px").set("vertical-align", "center");
		coverImageDiv.setWidthFull();
		coverImageDiv.setHeight(HEIGHT / 2 + "px");
			
		
		// LAYOUT DEBUG
		coverImageDiv.getStyle().set("border", "solid");
		add(coverImageDiv);
		
		String mileageContent = "km: ";
		
		VehicleDocument latestDocument = vc.getLatestVehicleDocument();
		if(latestDocument != null) {
			String mileage = latestDocument.getMileage().toString();
			mileageContent += mileage;
		}
		add(new H4(mileageContent));
		
		/*
		String currentMileage = vc.getCurrentMileage();
		if(StringUtils.isNotEmpty(currentMileage))
			add(new H4(currentMileage));
		
		for (String key : vc.getConfiguredDuties()) {
			
			VehicleDuty vd = vc.getDuty(key);
			
			VehicleDocument doc = null;
			
			if(vd != null) {
				doc = vd.getLatest();
				
				if(doc != null) {
					
					LocalDate ld = doc.getDate();
					String md = doc.getMileage();
					
					String date = ld != null ? ld.toString() : "";
					//String mileage = md != null ? md : "";
					
					//add(new H4(key + " " + date + " " + mileage));
				}
			}
			
			
		}
		*/
		
		setWidth(WIDTH, Unit.PIXELS);
        setHeight(HEIGHT, Unit.PIXELS);
        getStyle().set("overflow", "hidden").set("align-items", "center").set("margin", "5px");
        
        // LAYOUT DEBUG
        getStyle().set("border-style", "solid");
	}
}