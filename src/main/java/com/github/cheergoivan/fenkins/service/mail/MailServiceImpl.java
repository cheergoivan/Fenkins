package com.github.cheergoivan.fenkins.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String from, String receiver, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(receiver);
		message.setSubject(subject);
		message.setText(content);
		javaMailSender.send(message);
	}
}
