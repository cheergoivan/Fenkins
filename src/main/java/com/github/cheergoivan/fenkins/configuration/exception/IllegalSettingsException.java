package com.github.cheergoivan.fenkins.configuration.exception;

public class IllegalSettingsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6006957349238798284L;

	public IllegalSettingsException() {
		super();
	}

	public IllegalSettingsException(String message) {
		super(message);
	}

	public IllegalSettingsException(String message, Throwable cause) {
		super(message, cause);
	}
}
