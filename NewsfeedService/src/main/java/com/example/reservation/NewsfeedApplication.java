package com.example.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NewsfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsfeedApplication.class, args);
	}

}
