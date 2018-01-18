package com.github.cheergoivan.fenkins.configuration.exception;

public class InitializationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4994358180632431962L;

	public InitializationException() {
		super();
	}

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(String message) {
		super(message);
	}
}
