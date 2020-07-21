package com.boxoffice.application.service;

import java.util.Map;

import com.boxoffice.application.exception.MovieNotAllottedException;
import com.boxoffice.application.exception.MovieNotFoundException;
import com.boxoffice.application.exception.MultiplexNotFoundException;
import com.boxoffice.application.ro.MovieRO;
import com.boxoffice.application.ro.MultiplexRO;

public interface IBoxofficeService {
	public MovieRO searchMovie(String movieName) throws MovieNotFoundException;

	public MultiplexRO searchMultiplex(String multiplexName) throws MultiplexNotFoundException;

	public Map<String, MovieRO> allotMovieToMultiplex(String movieName, String multiplexName)
			throws MovieNotAllottedException;

}
