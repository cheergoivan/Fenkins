package com.github.cheergoivan.fenkins.entity.settings;

import java.util.List;

import com.github.cheergoivan.fenkins.entity.settings.executor.Executor;
import com.github.cheergoivan.fenkins.entity.settings.mail.MailProperties;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public class Settings {
	private List<Project> projects;
	private MailProperties mail;
	private Executor executor;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public MailProperties getMail() {
		return mail;
	}

	public void setMail(MailProperties mail) {
		this.mail = mail;
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}
}
