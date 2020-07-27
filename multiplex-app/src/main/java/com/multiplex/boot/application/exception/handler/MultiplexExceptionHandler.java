package com.multiplex.boot.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.multiplex.boot.application.exception.MultiplexNotAddedException;
import com.multiplex.boot.application.exception.MultiplexNotDeletedException;
import com.multiplex.boot.application.exception.MultiplexException;
import com.multiplex.boot.application.exception.MultiplexNotFoundException;
import com.multiplex.boot.application.exception.MultiplexNotModifiedException;

@ControllerAdvice
public class MultiplexExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MultiplexException.class)
	public final ResponseEntity<JsonResponse> handleAllExceptions(MultiplexException multiplexException,
			WebRequest request) {

		JsonResponse errorResponse = new JsonResponse(multiplexException.getMessage(), HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MultiplexNotAddedException.class)
	public ResponseEntity<JsonResponse> addMultiplexException(MultiplexNotAddedException multiplexNotAddedException) {

		JsonResponse errorResponse = new JsonResponse(multiplexNotAddedException.getMessage(),
				HttpStatus.NOT_FOUND.toString());

		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(MultiplexNotFoundException.class)
	public ResponseEntity<JsonResponse> getMultiplexException(MultiplexNotFoundException multiplexNotFoundException) {

		JsonResponse errorResponse = new JsonResponse(multiplexNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MultiplexNotModifiedException.class)
	public ResponseEntity<JsonResponse> multiplexNotModifiedException(
			MultiplexNotModifiedException multiplexNotModifiedException) {

		JsonResponse errorResponse = new JsonResponse(multiplexNotModifiedException.getMessage(),
				HttpStatus.NOT_MODIFIED.toString());
		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_MODIFIED);
	}

	@ExceptionHandler(MultiplexNotDeletedException.class)
	public ResponseEntity<JsonResponse> multiplexNotDeletedException(
			MultiplexNotDeletedException multiplexNotDeletedException) {

		JsonResponse errorResponse = new JsonResponse(multiplexNotDeletedException.getMessage(),
				HttpStatus.NOT_IMPLEMENTED.toString());
		return new ResponseEntity<JsonResponse>(errorResponse, HttpStatus.NOT_IMPLEMENTED);
	}

}
