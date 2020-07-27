package com.multiplex.boot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiplexApplication {

	public static void main(String[] args) {
		System.out.println("inside main");
		SpringApplication.run(MultiplexApplication.class, args);
	}

}
