package com.main;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.client.RestClientException;

@EnableEurekaServer
@SpringBootApplication
public class MainEureka {

	public static void main(String[] args) throws RestClientException, IOException {
		SpringApplication.run(MainEureka.class, args);

	}

}
