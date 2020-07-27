package com.multiplex.boot.application.exception;

public class MultiplexException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MultiplexException(String errorMsg) {
		super(errorMsg);
	}

}
