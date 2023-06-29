package com.pablolbasanta.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.pablolbasanta.tracking", "com.pablolbasanta.tracking.controllers"})
public class TrackingApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TrackingApplication.class, args);
	}

}
