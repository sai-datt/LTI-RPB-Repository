package com.movie.boot.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.movie.boot.application.exception.MovieException;
import com.movie.boot.application.exception.MovieNotAddedException;
import com.movie.boot.application.exception.MovieNotDeletedException;
import com.movie.boot.application.exception.MovieNotFoundException;
import com.movie.boot.application.exception.MovieNotModifiedException;

@ControllerAdvice
public class MovieExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MovieException.class)
	public final ResponseEntity<Object> handleAllExceptions(MovieException movieException) {

		JsonResponse errorResponse = new JsonResponse(movieException.getMessage(), HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MovieNotAddedException.class)
	public ResponseEntity<Object> addMovieException(MovieNotAddedException movieNotAddedException) {

		JsonResponse errorResponse = new JsonResponse(movieNotAddedException.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Object> getMovieException(MovieNotFoundException movieNotFoundException) {

		JsonResponse errorResponse = new JsonResponse(movieNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MovieNotModifiedException.class)
	public ResponseEntity<Object> movieNotModifiedException(MovieNotModifiedException movieNotModifiedException) {

		JsonResponse errorResponse = new JsonResponse(movieNotModifiedException.getMessage(),
				HttpStatus.NOT_MODIFIED.toString());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_MODIFIED);
	}

	@ExceptionHandler(MovieNotDeletedException.class)
	public ResponseEntity<JsonResponse> movieNotDeletedException(MovieNotDeletedException movieNotDeletedException) {

		JsonResponse errorResponse = new JsonResponse(movieNotDeletedException.getMessage(),
				HttpStatus.NOT_IMPLEMENTED.toString());

		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
	}

}
