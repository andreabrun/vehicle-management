package com.andreabrun.vehiclemanagement.entities;

import java.time.Period;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;
import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;
import com.andreabrun.vehiclemanagement.utils.PersistenceUtils;

@XmlRootElement
public class VehicleDocumentType {
	
	public static final String UNCATEGORIZED_DOCUMENT_KEY = "null";
	
	private Long id;
	
	@UserFillable(label = "Categoria", order = 0, type = UserFillable.TEXT)
	private String key;
	
	@UserFillable(label = "Invervallo di tempo", order = 1, type = UserFillable.PERIOD)
	private Period timeInterval;
	
	@UserFillable(label = "Intervallo di km", order = 2, type = UserFillable.INTEGER)
	private Integer mileageInterval;
	
	public VehicleDocumentType() {
		this(UNCATEGORIZED_DOCUMENT_KEY);
	}
	
	public VehicleDocumentType(String key) {
		this.id = PersistenceHelper.getNextID();
		this.key = key;
		this.mileageInterval = 0;
		this.timeInterval = Period.of(0, 0, 0);
	}
	
	@XmlElement
	public String getStringMileageInterval() {
		return mileageInterval.toString();
	}

	public void setStringMileageInterval(String mileageInterval) {
		this.mileageInterval = Integer.parseInt(mileageInterval);
	}

	@XmlTransient
	public int getMileageInterval() {
		return mileageInterval;
	}

	public void setMileageInterval(int mileageInterval) {
		this.mileageInterval = mileageInterval;
	}

	@XmlElement
    public Long getId() {
        return id;
    }
	
	public void setId(Long id) {
        this.id = id;
    }
	
	@XmlElement
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key= key ;
	}

	@XmlElement
	public String getStringTimeInterval() {
		return timeInterval.getYears() + PersistenceUtils.SEPARATOR + 
				timeInterval.getMonths() + PersistenceUtils.SEPARATOR + 
				timeInterval.getDays();
	}

	public void setStringTimeInterval(String timeInterval) {
		String[] split = timeInterval.split(PersistenceUtils.SEPARATOR);
		int timeIntervalY = Integer.parseInt(split[0]);
		int timeIntervalM = Integer.parseInt(split[1]);
		int timeIntervalD = Integer.parseInt(split[2]);
		this.timeInterval = Period.of(timeIntervalY, timeIntervalM, timeIntervalD);
	}
	
	@XmlTransient
	public Period getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Period timeInterval) {
		this.timeInterval = timeInterval;
	}
}
