package com.andreabrun.vehiclemanagement.utils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.vaadin.flow.server.VaadinSession;

@XmlRootElement
public class PersistenceHelper implements Persistable {
	
	private static String PERSISTENCEHELPER_FILENAME = "PH.xml";
	private static String PERSISTENCEHELPER_PATH = PersistenceUtils.PERSISTENCEHELPER_PATH + PERSISTENCEHELPER_FILENAME;
	
	private Long lastID;
	
	public PersistenceHelper() {
		lastID = 0L;
	}
	
	public Long getNext() {
		lastID++;
		return lastID;
	}
	
	public static PersistenceHelper getCurrent() {
		
		PersistenceHelper currentPersistenceHelper = VaadinSession.getCurrent().getAttribute(PersistenceHelper.class);
		
		if(currentPersistenceHelper == null) {
			
			try {
				if(PersistenceUtils.isFilePresent(PERSISTENCEHELPER_PATH, PERSISTENCEHELPER_FILENAME))
					currentPersistenceHelper = (PersistenceHelper) VehicleXMLService.unmarshalFromXML(PERSISTENCEHELPER_PATH, PersistenceHelper.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(currentPersistenceHelper == null) {
				currentPersistenceHelper = new PersistenceHelper();
			}
		}
		
		VaadinSession.getCurrent().setAttribute(PersistenceHelper.class, currentPersistenceHelper);
		
		return currentPersistenceHelper;
	}
	
	public static Long getNextID() {
		
		PersistenceHelper currentPersistenceHelper = getCurrent();
		
		Long id = currentPersistenceHelper.getNext();
		
		currentPersistenceHelper.persist();
		
		return id;
	}
	
	private String getFileName() {
		return PERSISTENCEHELPER_FILENAME;
	}
	
	public void persist() {
		try {
			VehicleXMLService.marshalToXML(this, PersistenceHelper.class, PERSISTENCEHELPER_PATH);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@XmlElement
    public Long getLastID() {
        return lastID;
    }

    public void setLastID(Long lastID) {
        this.lastID = lastID;
    }
}
