package com.andreabrun.vehiclemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andreabrun.vehiclemanagement.utils.PersistenceHelper;

@SpringBootApplication
public class VehiclemanagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VehiclemanagementApplication.class, args);
	}

}
