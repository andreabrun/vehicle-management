package com.andreabrun.vehiclemanagement;

import java.util.List;
import java.util.ArrayList;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@PageTitle("Vehicle Managment")
@Route(value="")
public class MainView extends AppLayout implements RouterLayout {

	private static final long serialVersionUID = 1L;

	public MainView() {
		
		DrawerToggle toggle = new DrawerToggle();

        H2 title = new H2("");
        title.getStyle().set("margin", "0");
        
        SideNav sideNav = new SideNav();
        
        sideNav.addItem(new SideNavItem("Home", HomeView.class));
        
        sideNav.addItem(new SideNavItem("Dashboard", DashboardView.class));
        
        sideNav.addItem(new SideNavItem("Configuration", ConfigurationView.class));
        
        sideNav.addItem(new SideNavItem("Documents", DocumentsView.class));
        
        sideNav.addItem(new SideNavItem("Gallery", GalleryView.class));
        
        sideNav.addItem(new SideNavItem("About", AboutView.class));
        
        Scroller scroller = new Scroller(sideNav);
       
        addToDrawer(scroller);

        addToNavbar(toggle, title);
        
        ComboBox<String> comboBox = new ComboBox<>();
        ArrayList<String> items = new ArrayList<String>();
        items.add("Honda Jazz");
        items.add("Nissan Terrano");
        items.add("Honda CB500F");
        items.add("Kawasaki EL252");
        comboBox.setItems(items);
        comboBox.getStyle().set("position", "absolute").set("right", "0").set("margin", "5px");
       
        addToNavbar(comboBox);
        
	}
}
