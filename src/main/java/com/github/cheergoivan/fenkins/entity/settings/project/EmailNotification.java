package com.github.cheergoivan.fenkins.entity.settings.project;

import java.util.Arrays;

public class EmailNotification {
	private Trigger trigger;
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
		ALWAYS, NEVER, ONLY_FAILURE
	}

	@Override
	public String toString() {
		return "EmailNotification [trigger=" + trigger + ", emails=" + Arrays.toString(emails) + "]";
	}
}