package com.github.cheergoivan.fenkins.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.cheergoivan.fenkins.web.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResourceNotFound(ResourceNotFoundException e) {
		return e.getMessage();
	}
}
