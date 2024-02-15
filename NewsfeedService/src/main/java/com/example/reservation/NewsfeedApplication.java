package com.example.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NewsfeedApplication {

	public static void main(String[] args) {
		System.out.println("🤔🤔🤔🤔NewsfeedApplication 실행");
		SpringApplication.run(NewsfeedApplication.class, args);
	}

}
