package com.andreabrun.vehiclemanagement;

import com.andreabrun.vehiclemanagement.entities.VehicleContainer;
import com.andreabrun.vehiclemanagement.entities.services.VehicleSessionBean;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Vehicle Managment")
@Route(value="")
public class MainView extends AppLayout implements RouterLayout {

	private static final long serialVersionUID = 1L;
	
	private VehicleSessionBean vsbean;
	
	private ComboBox<VehicleContainer> vcComboBox;

	public MainView() {
		
		init();
		
		initComponents();
		
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
	}
	
	public void init() {
		
		VaadinSession sesson = VaadinSession.getCurrent();
		
		PersistenceHelper ph = PersistenceHelper.getCurrent();
		sesson.setAttribute(PersistenceHelper.class, ph);
		
		VehicleSessionBean vsb = new VehicleSessionBean();
		vsb.init();
		sesson.setAttribute(VehicleSessionBean.class, vsb);
		
		this.vsbean = vsb;
	}
	
	public void initComponents() {
		vcComboBox = new ComboBox<>();
		vcComboBox.setItems(vsbean.getData());
		vcComboBox.setItemLabelGenerator(
                vc -> vc.getVehicle().getName()
        );
		vcComboBox.getStyle().set("position", "absolute").set("right", "0").set("margin", "5px");
       
        vsbean.addPropertyChangeListener(evt -> {
            if (VehicleSessionBean.VEHICLECONTAINERS.equals(evt.getPropertyName())) {
                update();
            }
        });
        
        vsbean.addPropertyChangeListener(evt -> {
            if (VehicleSessionBean.SELECTED.equals(evt.getPropertyName())) {
                vcComboBox.setValue(vsbean.getSelected());
            }
        });
        
        addToNavbar(vcComboBox);
        
	}
	
	public void update() {
		vcComboBox.setItems(vsbean.getData());
	}
}
