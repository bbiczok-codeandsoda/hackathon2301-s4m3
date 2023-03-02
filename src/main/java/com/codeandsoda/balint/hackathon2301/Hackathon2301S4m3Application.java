package com.codeandsoda.balint.hackathon2301;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hackathon2301S4m3Application {

	@Autowired
	static ClusterService clusterService;
	
	public static void main(String[] args) {
		SpringApplication.run(Hackathon2301S4m3Application.class, args);
		clusterService.clusterFlow();
	}

}
