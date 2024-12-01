package com.andreabrun.vehiclemanagement.utils;
import java.time.LocalDate;
import java.util.Comparator;
import com.andreabrun.vehiclemanagement.entities.VehicleDocument;

public class VehicleDocumentComparators {

	public static Comparator<VehicleDocument> DATE = new Comparator<VehicleDocument>() {
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
    
    
}