package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/gallery", layout = MainView.class)
public class GalleryView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public GalleryView() {
		add( new H1("Gallery title"));
	}
}
