package com.movieplex.boot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movieplex.boot.dto.MovieDTO;
import com.movieplex.boot.ro.MovieRO;
import com.movieplex.boot.service.IMovieService;

@Component
public class MovieResource {

	@Autowired
	IMovieService movieService;

	public MovieRO addMovie(MovieDTO movieDto) {
		System.out.println("inside resource");
		return movieService.addMovie(movieDto);
		
	}

	public MovieDTO getMovieDetails() {
		return null;
	}

	public List<MovieDTO> getMoviesList() {
		return null;
	}

	public MovieDTO updateMovieDetails() {
		return null;
	}

	public boolean deleteMovie() {
		return true;
	}
}
