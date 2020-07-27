package com.multiplex.boot.application.exception;

public class MultiplexNotAddedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MultiplexNotAddedException(String errorMsg) {
		super(errorMsg);
	}

}
