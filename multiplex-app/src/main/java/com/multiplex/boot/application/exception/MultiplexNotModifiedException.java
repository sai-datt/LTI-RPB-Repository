package com.multiplex.boot.application.exception;

public class MultiplexNotModifiedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MultiplexNotModifiedException(String errorMsg) {
		super(errorMsg);
	}

}
