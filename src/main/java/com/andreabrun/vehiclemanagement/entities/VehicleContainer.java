package com.andreabrun.vehiclemanagement.entities;

import com.andreabrun.vehiclemanagement.entities.services.Persistable;
import com.andreabrun.vehiclemanagement.entities.services.VehicleXMLService;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VehicleContainer implements Persistable {
	
	// The filename will be PERSISTENCE_KEY + id
	public static final String PERSISTENCE_KEY = "VehicleContainer";
	
	Long id;
	
	Vehicle master;

	String coverImagePath;
	
	List<String> configuredDuties;
	
	List<VehicleDuty> duties;
	
	public VehicleContainer() {
		this(null);
	}
	
	public VehicleContainer(Vehicle v) {
		id = PersistenceHelper.getNextID();
		master = v;
		configuredDuties = new ArrayList<String>();
		duties = new ArrayList<VehicleDuty>();
	}
	
	@XmlElement
    public Long getId() {
        return id;
    }
	
	@XmlElement(name = "vehicle")
    public Vehicle getVehicle() {
        return master;
    }
	
	public void setVehicle(Vehicle vehicle) {
        master = vehicle;
    }
	
	@XmlElement
    public String getCoverImagePath() {
        return coverImagePath;
    }
	
	public void setMasterCoverImage(String imageName) {
        this.coverImagePath = imageName;
    }
	
	@XmlElement
    public List<String> getConfiguredDuties() {
        return configuredDuties;
    }
	
	@XmlElement
    public List<VehicleDuty> getDuties() {
        return duties;
    }
	
	public List<VehicleDuty> getDuties(String key) {
		if(!configuredDuties.contains(key)) 
			return null;
		
		List<VehicleDuty> res = new ArrayList<VehicleDuty>();
		for(VehicleDuty vd : duties) {
			if(key.equals(vd.key))
				res.add(vd);
		}
		return res;
	}
	
	private String getFileName() {
		return PersistenceUtils.VEHICLE_PATH + PERSISTENCE_KEY + id.toString() + PersistenceUtils.EXT;
	}
	
	public void persist() {
		try {
			VehicleXMLService.marshalToXML(this, VehicleContainer.class, this.getFileName());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static List<VehicleContainer> findAll() {
		List<VehicleContainer> res = new ArrayList<VehicleContainer>();
		
		List<String> vcfiles = PersistenceUtils.getFilesInFolderWithKey(PersistenceUtils.VEHICLE_PATH, PERSISTENCE_KEY);
		for(String vcf : vcfiles) {
			VehicleContainer vc = null;
			try {
				vc = (VehicleContainer) VehicleXMLService.unmarshalFromXML(PersistenceUtils.VEHICLE_PATH + vcf, VehicleContainer.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(vc != null) {
				res.add(vc);
			}
		}
		
		return res;
	}
}
