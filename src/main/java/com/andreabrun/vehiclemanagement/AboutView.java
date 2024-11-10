package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/about", layout = MainView.class)
public class AboutView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public AboutView() {
		add( new H1("About title"));
	}
}
