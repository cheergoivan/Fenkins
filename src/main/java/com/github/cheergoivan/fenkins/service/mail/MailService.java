package com.github.cheergoivan.fenkins.service.mail;

public interface MailService {
	void sendMail(String from, String to, String subject, String content);
}
