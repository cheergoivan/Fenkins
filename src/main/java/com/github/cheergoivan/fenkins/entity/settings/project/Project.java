package com.github.cheergoivan.fenkins.entity.settings.project;

public class Project {
	private String name;
	private Git git = new Git();
	private Build build = new Build();
	private Deploy deploy = new Deploy();
	private EmailNotification notification = new EmailNotification();

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
