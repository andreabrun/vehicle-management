package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="/documents", layout = MainView.class)
public class DocumentsView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public DocumentsView() {
		add( new H1("Documents title"));
	}
}
