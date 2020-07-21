package com.boxoffice.application.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boxoffice.application.exception.MovieNotAllottedException;
import com.boxoffice.application.exception.MovieNotFoundException;
import com.boxoffice.application.exception.MultiplexNotFoundException;
import com.boxoffice.application.ro.MovieRO;
import com.boxoffice.application.ro.MultiplexRO;
import com.boxoffice.application.service.IBoxofficeService;

@Component
public class BoxofficeResource {

	@Autowired
	IBoxofficeService service;

	public MovieRO searchMovie(String movieName) throws MovieNotFoundException {
		return service.searchMovie(movieName);

	}

	public MultiplexRO searchMultiplex(String multiplexName) throws MultiplexNotFoundException {
		return service.searchMultiplex(multiplexName);
	}

	public Map<String, MovieRO> allotMovieToMultiplex(String movieName, String multiplexName)
			throws MovieNotAllottedException
{
		return service.allotMovieToMultiplex(movieName, multiplexName);
	}

}
