package com.github.cheergoivan.fenkins.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.github.cheergoivan.fenkins.configuration.exception.InitializationException;

@Configuration
@Order(1)
public class FenkinsStructureLoader {
	@Value("${fenkins.home}")
	private String fenkinsHome;
	
	@Bean
	public FenkinsStructure loadFenkinsStructure(){
		File dirFenkinsHome = new File(fenkinsHome);
		if(!dirFenkinsHome.exists()||!dirFenkinsHome.isDirectory())
			throw new InitializationException("Directory " + fenkinsHome +" doesn't exist!");
		return new FenkinsStructure(new File(fenkinsHome));
	}
}
