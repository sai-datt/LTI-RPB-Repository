package com.movieplex.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplex.boot.resource.MultiplexResource;
import com.movieplex.boot.ro.MultiplexRO;

@RestController
@RequestMapping("/multiplex-api")
public class MultiplexController {

	@Autowired
	MultiplexResource multiplexResource;
	
	@RequestMapping("/")
	public String healthCheck() {
		System.out.println("Multiplex health check");
		return "OK";
	}

	public ResponseEntity<MultiplexRO> addMultiplex() {
		return null;
	}

	public ResponseEntity<MultiplexRO> getMultiplexDetails() {
		return null;
	}

	public ResponseEntity<List<MultiplexRO>> getMultiplexList() {
		return null;
	}

	public ResponseEntity<MultiplexRO> updateMultiplexDetails() {
		return null;
	}

	public ResponseEntity<Boolean> deleteMultiplex() {
		return null;
	}

}
