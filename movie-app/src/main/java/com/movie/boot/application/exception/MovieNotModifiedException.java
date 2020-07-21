package com.movie.boot.application.exception;

public class MovieNotModifiedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotModifiedException(String errorMsg) {
		super(errorMsg);
	}

}
