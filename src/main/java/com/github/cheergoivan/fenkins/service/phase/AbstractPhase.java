package com.github.cheergoivan.fenkins.service.phase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPhase implements Phase {
	protected Context context;
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public AbstractPhase(Context context) {
		this.context = context;
	}

	public void throwPhaseExecutionFailureException(String message, Exception cause) {
		LOGGER.error(message, cause);
		PhaseExecutionFailureException ex = new PhaseExecutionFailureException(message, cause);
		try {
			ex.printStackTrace(new PrintWriter(new FileOutputStream(context.getLog().toFile(), true), true));
		} catch (FileNotFoundException e1) {
			LOGGER.error("File " + context.getLog() + " doesn't exist!", e1);
		}
		throw ex;
	}
	
	public void throwPhaseExecutionFailureException(String message) {
		LOGGER.error(message);
		PhaseExecutionFailureException ex = new PhaseExecutionFailureException(message);
		try {
			ex.printStackTrace(new PrintWriter(new FileOutputStream(context.getLog().toFile(), true), true));
		} catch (FileNotFoundException e1) {
			LOGGER.error("File " + context.getLog() + " doesn't exist!", e1);
		}
		throw ex;
	}

	public void log(Iterable<String> log) {
		try {
			Files.write(context.getLog(), log, StandardOpenOption.APPEND);
		} catch (IOException e) {
			LOGGER.error("Fail to write log!", e);
			throw new PhaseExecutionFailureException("Fail to write log!", e);
		}
	}
}
