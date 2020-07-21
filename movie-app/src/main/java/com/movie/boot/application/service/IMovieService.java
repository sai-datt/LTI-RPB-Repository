package com.movie.boot.application.service;

import java.util.List;

import com.movie.boot.application.dto.MovieDTO;
import com.movie.boot.application.exception.MovieException;
import com.movie.boot.application.ro.MovieRO;

public interface IMovieService {

	MovieRO addMovie(MovieDTO movieDto) throws MovieException;

	MovieRO getMovie(String id) throws MovieException;

	List<MovieRO> getMoviesList() throws MovieException;

	boolean deleteMovie(String id) throws MovieException;

	MovieRO updateMovie(MovieDTO movieDto) throws MovieException;

}
