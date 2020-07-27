package com.multiplex.boot.application.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.multiplex.boot.application.dao.IMultiplexRepository;
import com.multiplex.boot.application.document.MovieDocument;
import com.multiplex.boot.application.document.MultiplexDocument;
import com.multiplex.boot.application.dto.MultiplexDTO;
import com.multiplex.boot.application.exception.MultiplexNotAddedException;
import com.multiplex.boot.application.exception.MultiplexNotDeletedException;
import com.multiplex.boot.application.exception.MultiplexException;
import com.multiplex.boot.application.exception.MultiplexNotFoundException;
import com.multiplex.boot.application.exception.MultiplexNotModifiedException;
import com.multiplex.boot.application.ro.MovieRO;
import com.multiplex.boot.application.ro.MultiplexRO;
import com.multiplex.boot.application.service.IMultiplexService;

@Service
public class MultiplexServiceImpl implements IMultiplexService {

	@Autowired
	IMultiplexRepository multiplexRepository;

	@Autowired
	MongoTemplate template;

	@Override
	public MultiplexRO addMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotAddedException {

		List<String> movieNames = new ArrayList<String>();
		movieNames.add(multiplexDto.getMovieName());

		MultiplexDocument multiplex = new MultiplexDocument(null, multiplexDto.getName(), multiplexDto.getAddress(),
				multiplexDto.getNumberOfScreens(), movieNames);

		multiplex = multiplexRepository.save(multiplex);

		List<MovieRO> moviesList = new ArrayList<MovieRO>();
		Map<String, String> multiplexMap = new HashMap<String, String>();
		multiplexMap.put(multiplexDto.getName(), multiplexDto.getAddress());

		if (movieNames != null) {
			movieNames.forEach((name) -> {
				Query query = new Query(Criteria.where("name").is(name));

				MovieDocument movie = template.findOne(query, MovieDocument.class, "movie");

				MovieRO movieRo = new MovieRO(movie.getId(), movie.getName(), movie.getCategory(), movie.getProducer(),
						movie.getDirector(), movie.getReleaseDt(), movie.getLanguage(), multiplexMap);

				moviesList.add(movieRo);

			});
		}

		MultiplexRO multiplexRo = new MultiplexRO(multiplex.getId(), multiplex.getName(), multiplex.getAddress(),
				multiplex.getNumberOfScreens(), moviesList);
		return multiplexRo;
	}

	@Override
	public MultiplexRO getMultiplex(String id) throws MultiplexNotFoundException {

		Optional<MultiplexDocument> result = multiplexRepository.findById(id);

		if (result.isPresent()) {

			final List<MovieRO> moviesResponseList = new ArrayList<MovieRO>();

			// List<MovieDocument> movies = new ArrayList<MovieDocument>();

			List<String> movieNames = result.get().getMovieNames();

			if (movieNames != null) {

				movieNames.forEach((name) -> {
					Query query = new Query(Criteria.where("name").is(name));

					// Query to Movie DB for getting Movie details for each movie name
					MovieDocument temp = template.findOne(query, MovieDocument.class, "movie");

					// Map Movie document to response object and add to MovieRO list.
					MovieRO ro = new MovieRO();
					ro.setId(temp.getId());
					ro.setCategory(temp.getCategory());
					ro.setProducer(temp.getProducer());
					ro.setDirector(temp.getDirector());
					ro.setReleaseDt(temp.getReleaseDt());
					ro.setMultiplexMap(null);

					moviesResponseList.add(ro);

				});
			}

			MultiplexRO multiplexRo = new MultiplexRO(result.get().getId(), result.get().getName(),
					result.get().getAddress(), result.get().getNumberOfScreens(), moviesResponseList);

			return multiplexRo;
		} else {
			return null;
		}
	}

	@Override
	public List<MultiplexRO> geMultiplexList() throws MultiplexException {
		List<MultiplexDocument> resultList = multiplexRepository.findAll();
		List<MultiplexRO> responseList = new ArrayList<MultiplexRO>();
		resultList.forEach((document) -> {
			MultiplexRO ro = new MultiplexRO(document.getId(), document.getName(), document.getAddress(),
					document.getNumberOfScreens(), null);

			responseList.add(ro);
		});
		return responseList;
	}

	@Override
	public MultiplexRO updateMultiplex(MultiplexDTO multiplexDto) throws MultiplexNotModifiedException {
		Optional<MultiplexDocument> result = multiplexRepository.findById(multiplexDto.getId());

		List<String> movieNames = null;

		if (result.isPresent()) {
			movieNames = new ArrayList<String>();
			movieNames = result.get().getMovieNames();
			if (!(movieNames.contains(multiplexDto.getMovieName()))) {
				movieNames.add(multiplexDto.getMovieName());
			}

			MultiplexDocument multiplex = new MultiplexDocument(multiplexDto.getId(), multiplexDto.getName(),
					multiplexDto.getAddress(), multiplexDto.getNumberOfScreens(), movieNames);

			multiplex = multiplexRepository.save(multiplex);

			List<MovieRO> moviesList = new ArrayList<MovieRO>();
			Map<String, String> multiplexMap = new HashMap<String, String>();
			multiplexMap.put(multiplexDto.getName(), multiplexDto.getAddress());

			if (movieNames != null) {
				movieNames.forEach((name) -> {
					Query query = new Query(Criteria.where("name").is(name));

					MovieDocument movie = template.findOne(query, MovieDocument.class, "movie");

					MovieRO movieRo = new MovieRO(movie.getId(), movie.getName(), movie.getCategory(),
							movie.getProducer(), movie.getDirector(), movie.getReleaseDt(), movie.getLanguage(),
							multiplexMap);

					moviesList.add(movieRo);

				});
			}

			MultiplexRO multiplexRo = new MultiplexRO(multiplex.getId(), multiplex.getName(), multiplex.getAddress(),
					multiplex.getNumberOfScreens(), moviesList);

			return multiplexRo;
		}
		return null;
	}

	@Override
	public boolean deleteMultiplex(String id) throws MultiplexNotDeletedException {
		Optional<MultiplexDocument> result = multiplexRepository.findById(id);
		if (result.isPresent()) {
			multiplexRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
