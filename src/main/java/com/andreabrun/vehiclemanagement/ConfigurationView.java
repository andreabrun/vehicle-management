package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/configuration", layout = MainView.class)
public class ConfigurationView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public ConfigurationView() {
		add( new H1("Configuration title"));
	}
}
