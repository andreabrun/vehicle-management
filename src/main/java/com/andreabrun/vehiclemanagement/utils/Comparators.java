package com.andreabrun.vehiclemanagement.utils;

import java.time.LocalDate;
import java.util.Comparator;

import com.andreabrun.vehiclemanagement.entities.VehicleDocument;
import com.andreabrun.vehiclemanagement.entities.annotations.UserFillable;

public class Comparators {
	
	public static Comparator<VehicleDocument> VEHICLE_DOCUMENT_DATE = new Comparator<VehicleDocument>() {
		@Override
		public int compare(VehicleDocument vd1, VehicleDocument vd2) {
			LocalDate d1 = vd1.getDate();
			LocalDate d2 = vd2.getDate();
			
			if(d1 == null && d2 == null)
				return 0;
			
			if(d1 == null) 
				return -1;
			
			if(d2 == null)
				return 1;
			
			if(d1.isBefore(d2))
				return -1;
			
			if(d1.isAfter(d2))
				return 1;
			
			return 0;
		}
    };
    
    public static Comparator<UserFillable> USER_FILLABLE_ORDER = new Comparator<UserFillable>() {
    	@Override
    	public int compare(UserFillable uf1, UserFillable uf2) {
    		int o1 = uf1.order();
    		int o2 = uf2.order();
    		if(o1 < o2)	return -1;
    		if(o1 > o2) return 1;
    		return 0;
    	}
    };
    
}
