package com.github.cheergoivan.fenkins.configuration;

import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.entity.settings.mail.MailProperties;
import com.github.cheergoivan.fenkins.service.task.ProjectExecutor;

@Configuration
@AutoConfigureAfter(SettingsConfiguration.class)
public class EnvironmentConfiguration {
	@Autowired
	private Settings settings;

	@Bean
	public ProjectExecutor initializeExecutors() {
		ProjectExecutor executors = new ProjectExecutor();
		settings.getProjects().forEach(project -> {
			executors.register(project.getId());
		});
		return executors;
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		applyProperties(sender);
		try {
			sender.testConnection();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return sender;
	}

	private void applyProperties(JavaMailSenderImpl sender) {
		MailProperties properties = settings.getMail();
		if (properties != null) {
			sender.setHost(properties.getHost());
			sender.setPort(properties.getPort());
			sender.setUsername(properties.getUsername());
			sender.setPassword(properties.getPassword());
			sender.setProtocol(properties.getProtocol());
			sender.setDefaultEncoding("UTF-8");
			sender.setJavaMailProperties(asProperties(properties.getProperties()));
		}
	}

	private Properties asProperties(Map<String, String> source) {
		Properties properties = new Properties();
		properties.putAll(source);
		properties.putIfAbsent("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return properties;
	}
}
