package com.movie.boot.application.exception;

public class MovieNotDeletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotDeletedException(String errorMsg) {
		super(errorMsg);
	}

}
