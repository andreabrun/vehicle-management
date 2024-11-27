package com.andreabrun.vehiclemanagement.entities;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VehicleContainer {
	
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
	
	@XmlElement
    public Vehicle getVehicle() {
        return master;
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
}
