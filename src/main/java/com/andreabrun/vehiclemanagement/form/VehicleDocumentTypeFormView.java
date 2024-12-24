package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocumentType;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class VehicleDocumentTypeFormView extends VerticalLayout implements InputForm<VehicleDocumentType> {
	
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 300;

    private BeanValidationBinder<VehicleDocumentType> binder;
    
    private VehicleDocumentType docType;
    private VehicleContainer vc;

	public VehicleDocumentTypeFormView(VehicleDocumentType vdt, VehicleContainer vc) {
		
		this.docType = vdt;
		this.vc = vc;
		
        binder = new BeanValidationBinder<>(VehicleDocumentType.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(VehicleDocumentType.class, binder, vc);
        
        binder.readBean(this.docType);
        
        for(Component c : inputComponents) {
        	c.getStyle().set("width", WIDTH + "px");
        	add(c);
        }
	}
	
	@Override
	public void update(VehicleDocumentType vdt) {
		this.docType = vdt;
		binder.readBean(this.docType);
	}
	
	public VehicleDocumentType getVehicleDocumentType() {
		return this.docType;
	}
	
	public boolean validate() {
		if (binder.validate().isOk()) {
            try {
                binder.writeBean(this.docType);
                return true;
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		return false;
	}
}
