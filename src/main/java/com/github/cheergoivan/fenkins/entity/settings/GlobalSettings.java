package com.github.cheergoivan.fenkins.entity.settings;

import java.util.List;

import com.github.cheergoivan.fenkins.entity.settings.mail.MailProperties;
import com.github.cheergoivan.fenkins.entity.settings.project.Project;

public class GlobalSettings {
	private List<Project> projects;
	private MailProperties mail;

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
}
