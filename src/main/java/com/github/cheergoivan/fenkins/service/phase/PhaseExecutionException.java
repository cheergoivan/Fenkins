package com.github.cheergoivan.fenkins.service.phase;

public class PhaseExecutionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8525479235077356355L;
	
	public PhaseExecutionException() {
		super();
	}

	public PhaseExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhaseExecutionException(String message) {
		super(message);
	}
}
