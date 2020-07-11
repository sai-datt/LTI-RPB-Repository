package com.movieplex.boot.service;

import com.movieplex.boot.dto.MovieDTO;
import com.movieplex.boot.ro.MovieRO;

public interface IMovieService {
	public MovieRO addMovie(MovieDTO movieDto);

}
