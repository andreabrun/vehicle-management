package com.andreabrun.vehiclemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VehiclemanagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(VehiclemanagementApplication.class, args);
	}
	
	@Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(9090); // Set port programmatically
        return tomcat;
    }

}
