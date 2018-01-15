package com.github.cheergoivan.fenkins.service.phase;

import java.nio.file.Path;

import com.github.cheergoivan.fenkins.service.task.Task;

public class Context {
	private Path log;
	private Task task;
	
	public Path getLog() {
		return log;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
}
