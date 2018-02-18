package com.kkpa.trainingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TrainingCloudServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingCloudServerApplication.class, args);
	}
}
