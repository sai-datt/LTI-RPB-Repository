package com.movieplex.boot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieplexApplication {

	public static void main(String[] args) {
		System.out.println("inside main");
		SpringApplication.run(MovieplexApplication.class, args);
	}

}
