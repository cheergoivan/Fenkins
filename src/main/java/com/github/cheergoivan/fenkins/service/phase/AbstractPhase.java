package com.github.cheergoivan.fenkins.service.phase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPhase implements Phase {
	protected PhaseExecutionContext context;
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public AbstractPhase(PhaseExecutionContext context) {
		this.context = context;
	}
	
	public abstract boolean preExecute();
	
	public abstract void internalExecute();
	
	public void execute() {
		if(preExecute()) {
			internalExecute();
		}
	}

	public void throwPhaseExecutionException(String message, Exception cause) {
		LOGGER.error(message, cause);
		PhaseExecutionException ex = new PhaseExecutionException(message, cause);
		try {
			ex.printStackTrace(new PrintWriter(new FileOutputStream(context.getLog().toFile(), true), true));
		} catch (FileNotFoundException e1) {
			LOGGER.error("File " + context.getLog() + " doesn't exist!", e1);
		}
		throw ex;
	}
	
	public void throwPhaseExecutionException(String message) {
		LOGGER.error(message);
		throw new PhaseExecutionException(message);
	}

	public void log(Iterable<String> log) {
		try {
			Files.write(context.getLog(), log, StandardOpenOption.APPEND);
		} catch (IOException e) {
			LOGGER.error("Fail to write log!", e);
			throw new PhaseExecutionException("Fail to write log!", e);
		}
	}
	
	public void log(String message) {
		log(Arrays.asList(message));
	}

	public PhaseExecutionContext getContext() {
		return context;
	}
}
