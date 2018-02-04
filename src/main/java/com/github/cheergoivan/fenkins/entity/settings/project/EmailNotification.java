package com.github.cheergoivan.fenkins.entity.settings.project;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class EmailNotification {
	private Trigger trigger = Trigger.FAILURE;
	@NotNull(message = "Email addresses mustn't be null!")
	private String[] emails;

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public static enum Trigger {
		ALWAYS, NEVER, FAILURE
	}

	@Override
	public String toString() {
		return "EmailNotification [trigger=" + trigger + ", emails=" + Arrays.toString(emails) + "]";
	}
}