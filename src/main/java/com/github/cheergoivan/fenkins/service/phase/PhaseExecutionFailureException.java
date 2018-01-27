package com.github.cheergoivan.fenkins.service.phase;

public class PhaseExecutionFailureException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8525479235077356355L;

	public PhaseExecutionFailureException() {
		super();
	}

	public PhaseExecutionFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhaseExecutionFailureException(String message) {
		super(message);
	}
}
