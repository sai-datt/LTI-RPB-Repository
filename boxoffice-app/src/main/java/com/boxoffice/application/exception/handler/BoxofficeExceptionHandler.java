package com.boxoffice.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.boxoffice.application.exception.MovieNotAllottedException;
import com.boxoffice.application.exception.MovieNotFoundException;
import com.boxoffice.application.exception.MultiplexNotFoundException;

@ControllerAdvice
public class BoxofficeExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MovieNotAllottedException.class)
	public ResponseEntity<JsonResponse> movieNotAllottedException(MovieNotAllottedException movieNotAllottedException) {

		JsonResponse errorResponse = new JsonResponse(movieNotAllottedException.getMessage(),
				HttpStatus.NOT_IMPLEMENTED.toString());

		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<JsonResponse> movieNotFoundException(MovieNotFoundException movieNotFoundException) {
		
		JsonResponse errorResponse = new JsonResponse(movieNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MultiplexNotFoundException.class)
	public ResponseEntity<Object> multiplexNotFoundException(MultiplexNotFoundException multiplexNotFoundException) {
		
		JsonResponse errorResponse = new JsonResponse(multiplexNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);

	}

}
