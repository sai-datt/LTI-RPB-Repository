package com.movieplex.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieplex.boot.dto.MovieDTO;
import com.movieplex.boot.resource.MovieResource;
import com.movieplex.boot.ro.MovieRO;

@RestController
@RequestMapping("/movie-api")
public class MovieController {

	@Autowired
	MovieResource movieResource;

	/*
	 * @RequestMapping("/") public String healthCheck() {
	 * System.out.println("Movie health check"); return "OK"; }
	 */

	@PostMapping("/addMovie")
	public ResponseEntity<MovieRO> addMovie(@RequestBody MovieDTO movieDto) {

		MovieRO movieRo = movieResource.addMovie(movieDto);
		System.out.println("in controller");
		ResponseEntity<MovieRO> response = 
				new ResponseEntity<MovieRO>(movieRo, HttpStatus.OK);
		return response;
	}

	public ResponseEntity<MovieRO> getMovieDetails() {
		return null;
	}

	public ResponseEntity<List<MovieRO>> getMoviesList() {
		return null;
	}

	public ResponseEntity<MovieRO> updateMovieDetails() {
		return null;
	}

	public ResponseEntity<Boolean> deleteMovie() {
		return null;
	}

}
