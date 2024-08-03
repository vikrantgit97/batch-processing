package com.example;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchProcessingApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BatchProcessingApplication.class)
			.web(WebApplicationType.SERVLET) //If we add an endpoint, otherwise we can use NONE
			.run(args)
			.registerShutdownHook();
	}

}
