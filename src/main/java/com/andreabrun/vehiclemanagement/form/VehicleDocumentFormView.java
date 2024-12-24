package com.andreabrun.vehiclemanagement.form;

import java.util.List;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class VehicleDocumentFormView extends VerticalLayout implements InputForm<VehicleDocument> {
	
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 300;

    private BeanValidationBinder<VehicleDocument> binder;
    
    private VehicleDocument doc;
    private VehicleContainer vc;
    
    public VehicleDocumentFormView() {
		this(new VehicleDocument(), null);
    }

	public VehicleDocumentFormView(VehicleDocument vd, VehicleContainer vc) {
		
		this.doc = vd;
		this.vc = vc;
		
        binder = new BeanValidationBinder<>(VehicleDocument.class);

        List<Component> inputComponents = InputFormUtils.createInputComponents(VehicleDocument.class, binder, vc);
        
        binder.readBean(this.doc);
        
        for(Component c : inputComponents) {
        	c.getStyle().set("width", WIDTH + "px");
        	add(c);
        }
	}
	
	@Override
	public void update(VehicleDocument vd) {
		this.doc = vd;
		binder.readBean(this.doc);
	}
	
	public VehicleDocument getVehicleDocument() {
		return this.doc;
	}
	
	public boolean validate() {
		if (binder.validate().isOk()) {
            try {
                binder.writeBean(this.doc);
                return true;
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		return false;
	}
}
