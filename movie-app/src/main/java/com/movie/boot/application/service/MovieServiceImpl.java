package com.movie.boot.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.movie.boot.application.controller.MovieController;
import com.movie.boot.application.dao.IMovieRepository;
import com.movie.boot.application.document.MovieDocument;
import com.movie.boot.application.document.MultiplexDocument;
import com.movie.boot.application.dto.MovieDTO;
import com.movie.boot.application.exception.MovieNotAddedException;
import com.movie.boot.application.exception.MovieException;
import com.movie.boot.application.exception.MovieNotFoundException;
import com.movie.boot.application.exception.MovieNotModifiedException;
import com.movie.boot.application.ro.MovieRO;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Autowired
	MongoTemplate template;

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Override
	public MovieRO addMovie(MovieDTO movieDto) throws MovieNotAddedException {

		MovieDocument movie = new MovieDocument(movieDto.getId(), movieDto.getName(), movieDto.getCategory(),
				movieDto.getProducer(), movieDto.getDirector(), movieDto.getReleaseDt(), movieDto.getLanguage(), null);

		movie = movieRepository.save(movie);

		MovieRO movieRo = new MovieRO(movie.getId(), movie.getName(), movie.getCategory(), movie.getProducer(),
				movie.getDirector(), movie.getReleaseDt(), movie.getLanguage(), null);

		return movieRo;
	}

	@Override
	public MovieRO getMovie(String id) throws MovieNotFoundException {

		Optional<MovieDocument> result = movieRepository.findById(id);
		logger.debug("inside service impl..input param:" + id);
		logger.debug("result is:" + result);

		if (result.isPresent()) {

			List<String> multiplexNames = result.get().getMultiplexNames();
			final Map<String, String> multiplexMap = new HashMap<String, String>();

			if (multiplexNames != null) {

				multiplexNames.forEach((name) -> {
					Query query = new Query(Criteria.where("name").is(name));
					MultiplexDocument temp = template.findOne(query, MultiplexDocument.class, "multiplex");
					multiplexMap.put(temp.getName(), temp.getAddress());
				});
			}

			MovieRO movieRo = new MovieRO(result.get().getId(), result.get().getName(), result.get().getCategory(),
					result.get().getProducer(), result.get().getDirector(), result.get().getReleaseDt(),
					result.get().getLanguage(), multiplexMap);

			return movieRo;
		} else {
			return null;
		}
	}

	@Override
	public List<MovieRO> getMoviesList() throws MovieException {

		List<MovieDocument> resultList = movieRepository.findAll();
		List<MovieRO> responseList = new ArrayList<MovieRO>();
		resultList.forEach((document) -> {
			MovieRO ro = new MovieRO(document.getId(), document.getName(), document.getCategory(),
					document.getProducer(), document.getDirector(), document.getReleaseDt(), document.getLanguage(),
					null);

			responseList.add(ro);
		});
		return responseList;
	}

	@Override
	public MovieRO updateMovie(MovieDTO movieDto) throws MovieNotModifiedException {

		Optional<MovieDocument> result = movieRepository.findById(movieDto.getId());

		if (result.isPresent()) {
			MovieDocument movie = new MovieDocument(movieDto.getId(), movieDto.getName(), movieDto.getCategory(),
					movieDto.getProducer(), movieDto.getDirector(), movieDto.getReleaseDt(), movieDto.getLanguage(),
					null);

			movie = movieRepository.save(movie);

			MovieRO movieRo = new MovieRO(movie.getId(), movie.getName(), movie.getCategory(), movie.getProducer(),
					movie.getDirector(), movie.getReleaseDt(), movie.getLanguage(), null);

			return movieRo;
		}
		return null;
	}

	@Override
	public boolean deleteMovie(String id) throws MovieException {
		Optional<MovieDocument> result = movieRepository.findById(id);
		if (result.isPresent()) {
			movieRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
