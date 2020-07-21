package com.movie.boot.application.exception;

public class MovieNotAddedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotAddedException(String errorMsg) {
		super(errorMsg);
	}

}
