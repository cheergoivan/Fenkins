package com.github.cheergoivan.fenkins.entity.settings.project;

import org.hibernate.validator.constraints.NotBlank;

public class Deploy {
	@NotBlank(message = "Deploy command mustn't be null!")
	private String command;
	private long timeout = -1;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "Deployment [command=" + command + ", timeout=" + timeout + "]";
	}
}
