package com.movie.boot.application.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movie.boot.application.dto.MovieDTO;
import com.movie.boot.application.exception.MovieNotAddedException;
import com.movie.boot.application.exception.MovieException;
import com.movie.boot.application.exception.MovieNotFoundException;
import com.movie.boot.application.exception.MovieNotModifiedException;
import com.movie.boot.application.ro.MovieRO;
import com.movie.boot.application.service.IMovieService;

@Component	
public class MovieResource {
	
	@Autowired
	IMovieService movieService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieResource.class);

	
	public MovieRO addMovie(MovieDTO movieDto) throws MovieNotAddedException{
		logger.debug("Inside resource..calling service");
		return movieService.addMovie(movieDto);
		
	}
	
	public MovieRO getMovie(String id) throws MovieNotFoundException{
		logger.debug("calling service");

		return movieService.getMovie(id);
	}

	public List<MovieRO> getMoviesList() throws MovieException{
		return movieService.getMoviesList();
		
	}
	
	public MovieRO updateMovie(MovieDTO movieDto) throws MovieNotModifiedException{
		return movieService.updateMovie(movieDto);
		
	}

	public boolean deleteMovie(String id){
		return movieService.deleteMovie(id);
	}
}
