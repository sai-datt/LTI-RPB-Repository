package com.boxoffice.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boxoffice.application.constants.BoxofficeConstants;
import com.boxoffice.application.exception.MovieNotAllottedException;
import com.boxoffice.application.exception.MovieNotFoundException;
import com.boxoffice.application.exception.MultiplexNotFoundException;
import com.boxoffice.application.ro.MovieRO;
import com.boxoffice.application.ro.MultiplexRO;
import com.mongodb.client.result.UpdateResult;

@Service
public class BoxofficeServiceImpl implements IBoxofficeService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public MovieRO searchMovie(String name) throws MovieNotFoundException {
		if (restTemplate != null) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		// MovieRO movieRo = new MovieRO();

		// movieRo = restTemplate.getForObject(BoxofficeConstants.MOVIE_URL + "?id=" +
		// name, MovieRO.class);

		ResponseEntity<MovieRO> temp = new ResponseEntity<MovieRO>(new MovieRO(), HttpStatus.OK);
		temp = restTemplate.getForEntity(BoxofficeConstants.MOVIE_URL + "?id=" + name, MovieRO.class);

		// System.out.println(temp.getBody().toString());

		if (temp.hasBody()) {
			System.out.println("Has body");
		} else {
			System.out.println("IT HAS NO BODY");
		}

		// System.out.println(temp.getStatusCode());

		return null;
	}

	@Override
	public MultiplexRO searchMultiplex(String name) throws MultiplexNotFoundException {

		MultiplexRO multiplexRo = restTemplate.getForObject(BoxofficeConstants.MULTIPLEX_URL + "?id=" + name,
				MultiplexRO.class);

		if (multiplexRo != null) {
			return multiplexRo;
		} else {
			return null;
		}
	}

	@Override
	public Map<String, MovieRO> allotMovieToMultiplex(String movieName, String multiplexName)
			throws MovieNotAllottedException {

		MovieRO updatedMovieObj = new MovieRO();
		Map<String, MovieRO> responseMap = new HashMap<String, MovieRO>();

		// Retrieve multiplex details with multiplex name
		MultiplexRO multiplexRo = this.restTemplate
				.getForObject(BoxofficeConstants.MULTIPLEX_URL + "?id=" + multiplexName, MultiplexRO.class);

		if (multiplexRo != null) {

			// Retrieve existing movie objects in the multiplex into a list
			List<MovieRO> existingMovieObjs = new ArrayList<MovieRO>();
			existingMovieObjs = multiplexRo.getMovies();

			// Declare list to retrieve existing movie names from multiplex response object
			List<String> existingMovieNames = new ArrayList<String>();

			// Iterate through existing movie objects to check whether the input movie name
			// already exists in the multiplex. If not, get the movie name from that movie
			// object and store in the existingMovieNames list.
			existingMovieObjs.forEach((singleMovieObj) -> {

				if (singleMovieObj.getName().equals(movieName)) {
					String errorMessage = "Movie already running in " + multiplexName + "."
							+ "Please choose another multiplex. \n Thank you!";

					throw new MovieNotAllottedException(errorMessage);
				}
			});

			existingMovieObjs.forEach((movieObj) -> {

				existingMovieNames.add(movieObj.getName());
			});

			// Check whether multiplex has vacancy to allot a movie. If yes, add the input
			// movie name to existing movie names list and update that particular document
			// in multiplex collection.
			// Also, update the multiplexes list for that movie in movie collection.

			if (multiplexRo.getMovies().size() < multiplexRo.getNumberOfScreens()) {

				// Update multiplex document with new movie
				Query multiplexQuery = new Query(Criteria.where("name").is(multiplexName));

				Update multiplexUpdate = new Update();
				Update.update(BoxofficeConstants.MOVIE_NAMES_COLUMN, existingMovieNames.add(movieName));

				UpdateResult multiplexUpdateResult = mongoTemplate.updateFirst(multiplexQuery, multiplexUpdate,
						BoxofficeConstants.MULTIPLEX_COLLECTION);

				System.out.println(
						multiplexUpdateResult.getModifiedCount() + " " + "row modified in Multiplex collection!");

				System.out.println("Document ID for updated record:" + multiplexUpdateResult.getUpsertedId());
			} else {

				String errorMessage = "Multiplex is full! Please choose another multiplex. \n Thank you!";
				responseMap.put(errorMessage, null);
				
				throw new MovieNotAllottedException(errorMessage);

			}
			// Update movie document with new multiplex

			MovieRO movieRo = this.restTemplate.getForObject(BoxofficeConstants.MOVIE_URL + "?id=" + movieName,
					MovieRO.class);

			// Retrieve existing multiplex names from Movie response object
			List<String> existingMultiplexNames = new ArrayList<String>();
			movieRo.getMultiplexMap().forEach((multiplex, address) -> {
				existingMultiplexNames.add(multiplex);
			});

			if (existingMultiplexNames.contains(multiplexName)) {
				responseMap.put("Movie already allocated", movieRo);
				return responseMap;
			} else {
				Query movieQuery = new Query(Criteria.where("name").is(movieName));

				Update movieUpdate = new Update();
				Update.update(BoxofficeConstants.MULTIPLEX_NAMES_COLUMN, existingMultiplexNames.add(multiplexName));

				UpdateResult movieUpdateResult = mongoTemplate.updateFirst(movieQuery, movieUpdate,
						BoxofficeConstants.MOVIE_COLLECTION);

				movieRo.getMultiplexMap().put(multiplexName, multiplexRo.getAddress());

				System.out.println(movieUpdateResult.getModifiedCount() + " " + "row modified in Movie collection!");

				System.out.println("Document ID for updated record:" + movieUpdateResult.getUpsertedId());

				updatedMovieObj = movieRo;
				responseMap.put("Movie allocated successfully!", updatedMovieObj);

				return responseMap;

			}

		} else {
			String errorMessage = "This Multiplex is not available! Please try in another multiplex!";
			responseMap.put(errorMessage, null);
		}

		return responseMap;
	}

}
