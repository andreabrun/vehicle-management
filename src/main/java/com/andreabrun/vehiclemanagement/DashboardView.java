package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/dashboard", layout = MainView.class)
public class DashboardView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public DashboardView() {
		add( new H1("Dashboard title"));
	}
}
