package com.andreabrun.vehiclemanagement;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@PageTitle("Vehicle Managment")
@Route(value="")
public class MainView extends AppLayout implements RouterLayout {

	private static final long serialVersionUID = 1L;

	public MainView() {
		
		DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Vehicle Managment Application");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        
        SideNav sideNav = new SideNav();
        
        sideNav.addItem(new SideNavItem("Home", MainView.class));
        
        sideNav.addItem(new SideNavItem("Dashboard", DashboardView.class));
        
        sideNav.addItem(new SideNavItem("Configuration", ConfigurationView.class));
        
        sideNav.addItem(new SideNavItem("Documents"));
        
        sideNav.addItem(new SideNavItem("About"));
        
        Scroller scroller = new Scroller(sideNav);
       
        addToDrawer(scroller);

        addToNavbar(toggle, title);
	}
}
