package com.movie.boot.application.exception;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
