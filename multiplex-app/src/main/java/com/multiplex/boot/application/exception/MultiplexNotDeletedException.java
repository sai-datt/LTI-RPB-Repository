package com.multiplex.boot.application.exception;

public class MultiplexNotDeletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MultiplexNotDeletedException(String errorMsg) {
		
		super(errorMsg);
		
	}

}
