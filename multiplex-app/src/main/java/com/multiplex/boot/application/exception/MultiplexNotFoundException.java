package com.multiplex.boot.application.exception;

public class MultiplexNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MultiplexNotFoundException(String errorMsg) {
		super(errorMsg);
	}

}
