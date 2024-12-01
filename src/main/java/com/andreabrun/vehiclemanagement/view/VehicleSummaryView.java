package com.andreabrun.vehiclemanagement.view;

import java.io.File;
import java.time.LocalDate;

import com.andreabrun.vehiclemanagement.entities.Vehicle;
import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.VehicleDuty;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinService;

import io.micrometer.common.util.StringUtils;

public class VehicleSummaryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    private static final String DEFAULT_COVERIMAGE_NAME = "altcaricon.webp";

	public VehicleSummaryView(VehicleContainer vc, int width) {
		
		super();
		
		Vehicle vehicle = vc.getVehicle();
		
		String name = vehicle.getName();
		add(new H2(name));

		
		Image coverImage = null;
		if(vc.isCoverImagePresent()) {
			String coverImagePath = vc.getCoverImagePath();
			coverImage = new Image(coverImagePath, "Cover image");
		} else {
			coverImage = new Image(PersistenceUtils.ASSETS_PATH + DEFAULT_COVERIMAGE_NAME, "Cover image");
			VaadinService.getCurrent();
		}
		add(coverImage);
		
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
					String mileage = md != null ? md : "";
					
					add(new H4(key + " " + date + " " + mileage));
				}
			}
			
			
		}
		
		setWidth(width, Unit.PIXELS);
        
	}
}