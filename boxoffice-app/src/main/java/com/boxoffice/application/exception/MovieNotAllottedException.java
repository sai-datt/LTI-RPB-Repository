package com.boxoffice.application.exception;

public class MovieNotAllottedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotAllottedException(String errorMsg) {
		super(errorMsg);
	}
}
