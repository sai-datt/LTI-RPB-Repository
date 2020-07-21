package com.boxoffice.application.exception;

public class BoxofficeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoxofficeException(String errorMsg) {
		super(errorMsg);
	}

}
