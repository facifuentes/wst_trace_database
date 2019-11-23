package com.edu.usbcali.wst_database_trace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.edu.usbcali.wst_database_trace.*" })
public class WstDatabaseTraceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WstDatabaseTraceApplication.class, args);
	}

}