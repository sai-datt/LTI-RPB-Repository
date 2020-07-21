package com.boxoffice.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boxoffice.application.exception.MovieNotAllottedException;
import com.boxoffice.application.exception.MovieNotFoundException;
import com.boxoffice.application.exception.MultiplexNotFoundException;
import com.boxoffice.application.resource.BoxofficeResource;
import com.boxoffice.application.ro.MovieRO;
import com.boxoffice.application.ro.MultiplexRO;

@RestController()
@RequestMapping("/boxoffice-api")
public class BoxofficeController {

	@Autowired
	BoxofficeResource resource;

	@RequestMapping("/")
	public ResponseEntity<String> healthCheck() {
		String status = "Boxoffice is open!";
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@GetMapping("/searchMovie")
	public ResponseEntity<MovieRO> searchMovie(@RequestParam(name = "id") String movieId)
			throws MovieNotFoundException {

		ResponseEntity<MovieRO> response = null;

		MovieRO movieRo = resource.searchMovie(movieId);
		if (movieRo != null) {
			response = new ResponseEntity<MovieRO>(movieRo, HttpStatus.OK);
		} else {
			throw new MovieNotFoundException(
					"The movie you are searching for seems to be unavailable. Please try after sometime");
		}

		return response;
	}

	@GetMapping("/searchMultiplex")
	public ResponseEntity<MultiplexRO> searchMultiplex(@RequestParam(name = "id") String multiplexId)
			throws MultiplexNotFoundException {

		ResponseEntity<MultiplexRO> response = null;

		MultiplexRO multiplexRo = resource.searchMultiplex(multiplexId);

		if (multiplexRo != null) {
			response = new ResponseEntity<MultiplexRO>(multiplexRo, HttpStatus.OK);
		} else {
			throw new MultiplexNotFoundException("The multiplex you are searching is not found.");
		}
		return response;
	}

	@PutMapping("/allotMovie")
	public ResponseEntity<Map<String, MovieRO>> allotMovieToMultiplex(@RequestParam(name = "movieId") String movieId,
			@RequestParam(name = "multiplexId") String multiplexId) throws MovieNotAllottedException {

		ResponseEntity<Map<String, MovieRO>> response = null;

		Map<String, MovieRO> responseMap = new HashMap<String, MovieRO>();

		responseMap = resource.allotMovieToMultiplex(movieId, multiplexId);

		if (responseMap != null) {
			response = new ResponseEntity<Map<String, MovieRO>>(responseMap, HttpStatus.OK);
		} else {
			throw new MovieNotAllottedException("Cannot allot the movie " + movieId + "to the multiplex "
					+ multiplexId + "." + "\n Please verify the details once again. Thank you!");
		}

		return response;
	}

}
