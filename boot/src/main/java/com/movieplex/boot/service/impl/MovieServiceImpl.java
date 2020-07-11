package com.movieplex.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieplex.boot.dao.IMovieRepository;
import com.movieplex.boot.document.MovieDocument;
import com.movieplex.boot.dto.MovieDTO;
import com.movieplex.boot.ro.MovieRO;
import com.movieplex.boot.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	public MovieRO addMovie(MovieDTO movieDto) {
		System.out.println("inside service");
		MovieDocument movieDocument = new MovieDocument(null, 
				movieDto.getName(), movieDto.getCategory(),
				movieDto.getDirector(), movieDto.getProducer(), 
				movieDto.getReleaseDt(), movieDto.getGenre());
		
		System.out.println("Calling mongo repository");
		//this part i have doubt. we are reassigning data to same object after inserting??
		//typehere only
		//
//		movieDocument = movieRepository.save(movieDocument);
		MovieDocument movie = movieRepository.save(movieDocument);
		MovieRO movieRo = new MovieRO(movie.getId(), 
				movie.getName(), movie.getCategory(),
				movie.getDirector(), movie.getProducer(), 
				movie.getReleaseDt());
		
		System.out.println("Movie document:" + "-" + 
		movieRo.getName() + "-" + movieRo.getCategory() + "-"
				+ movieRo.getDirector() + "-" + movieRo.getProducer() 
				+ movieRo.getReleaseDt());
		
		return movieRo;
	}

}
