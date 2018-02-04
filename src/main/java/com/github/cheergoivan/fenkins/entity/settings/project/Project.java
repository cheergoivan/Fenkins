package com.github.cheergoivan.fenkins.entity.settings.project;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Project {
	private String id;
	@NotNull(message = "Project name mustn't be null!")
	private String name;
	@Valid
	private Git git;
	@Valid
	private Build build;
	@Valid
	private Deploy deploy;
	@Valid
	private EmailNotification notification;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Git getGit() {
		return git;
	}

	public void setGit(Git git) {
		this.git = git;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}

	public Deploy getDeploy() {
		return deploy;
	}

	public void setDeploy(Deploy deploy) {
		this.deploy = deploy;
	}

	public EmailNotification getNotification() {
		return notification;
	}

	public void setNotification(EmailNotification notification) {
		this.notification = notification;
	}
}
