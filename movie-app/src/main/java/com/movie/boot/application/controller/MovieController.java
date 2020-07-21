package com.movie.boot.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.boot.application.dto.MovieDTO;
import com.movie.boot.application.exception.MovieNotAddedException;
import com.movie.boot.application.exception.MovieNotDeletedException;
import com.movie.boot.application.exception.MovieException;
import com.movie.boot.application.exception.MovieNotFoundException;
import com.movie.boot.application.exception.MovieNotModifiedException;
import com.movie.boot.application.resource.MovieResource;
import com.movie.boot.application.ro.MovieRO;

@RestController
@RequestMapping("/movie-api")
public class MovieController {

	@Autowired
	MovieResource movieResource;

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@RequestMapping("/")
	public ResponseEntity<String> healthCheck() {
		String status = "Movie is running!";
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@PostMapping("/addMovie")
	public ResponseEntity<MovieRO> addMovie(@RequestBody MovieDTO movieDto) throws MovieNotAddedException {

		ResponseEntity<MovieRO> response = null;

		MovieRO movieRo = movieResource.addMovie(movieDto);
		if (movieRo != null) {

			response = new ResponseEntity<MovieRO>(movieRo, HttpStatus.OK);
		} else {
			logger.error("Error while adding movie details with name:" + movieDto.getName());
			throw new MovieNotAddedException("Error while adding movie");
		}

		return response;
	}

	@GetMapping("/getMovie")
	public ResponseEntity<MovieRO> getMovie(@RequestParam(name = "id") String id) throws MovieNotFoundException {

		ResponseEntity<MovieRO> response = null;

		MovieRO movieRo = movieResource.getMovie(id);
		if (movieRo != null) {
			response = new ResponseEntity<MovieRO>(movieRo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<MovieRO>(movieRo, HttpStatus.NOT_FOUND);
			//throw new MovieNotFoundException("Please verify the movie id and try again!\n Given movie id is:" + id);
		}

		return response;
	}

	@GetMapping("/getMoviesList")
	public ResponseEntity<List<MovieRO>> getMoviesList() throws MovieException {

		ResponseEntity<List<MovieRO>> response = null;
		List<MovieRO> moviesList = movieResource.getMoviesList();
		if (moviesList != null) {

			response = new ResponseEntity<List<MovieRO>>(moviesList, HttpStatus.OK);

		} else {
			logger.error("Error while retrieving movies list");
			throw new MovieException("There are no movies at present or cannot retrieve movies list. "
					+ "\n Please try after sometime!");
		}

		return response;
	}

	@PutMapping("/updateMovie")
	public ResponseEntity<MovieRO> updateMovie(@RequestBody MovieDTO movieDto) throws MovieNotModifiedException {

		ResponseEntity<MovieRO> response = null;

		MovieRO movieRo = movieResource.updateMovie(movieDto);
		if (movieRo != null) {

			response = new ResponseEntity<MovieRO>(movieRo, HttpStatus.OK);

		} else {
			logger.error("Error while updating movie details with id:" + movieDto.getId());
			throw new MovieNotModifiedException("Error while updating details of the movie " + movieDto.getName()
					+ "having id " + movieDto.getId());
		}

		return response;
	}

	@DeleteMapping("/deleteMovie")
	public ResponseEntity<List<Object>> deleteMovie(@RequestParam(name = "id") String id)
			throws MovieNotDeletedException {

		ResponseEntity<List<Object>> response = null;
		List<Object> responseObject = new ArrayList<Object>();

		boolean status = movieResource.deleteMovie(id);
		if (status) {
			responseObject.add("Movie with id:" + id + "deleted successfully!");
			responseObject.add(HttpStatus.OK);
			responseObject.add("Deletion status: SUCCESS");

			response = new ResponseEntity<List<Object>>(responseObject, HttpStatus.OK);

		} else {
			logger.error("Error while deleting movie details with id:" + id);

			responseObject.add("Movie with id:" + id + " not found! Please verify the movie ID once");
			responseObject.add(HttpStatus.NOT_IMPLEMENTED);
			responseObject.add("Deletion status: FAILURE");

			throw new MovieNotDeletedException(responseObject.toString());

		}
		return response;
	}

}
