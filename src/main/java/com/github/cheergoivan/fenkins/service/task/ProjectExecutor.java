package com.github.cheergoivan.fenkins.service.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProjectExecutor {
	/**
	 * key: projectId
	 * value: Executor
	 */
	private Map<String, Executor> executors = new HashMap<>();
	
	public void register(String projectId){
		executors.put(projectId, Executors.newSingleThreadExecutor());
	}
	
	public void execute(String projectId, Runnable task){
		if(executors.containsKey(projectId)){
			executors.get(projectId).execute(task);
		}
	}
}
