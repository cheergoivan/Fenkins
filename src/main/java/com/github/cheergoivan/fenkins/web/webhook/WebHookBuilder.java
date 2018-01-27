package com.github.cheergoivan.fenkins.web.webhook;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

public class WebHookBuilder {
	
	private WebHookBuilder(){}
	
	public static String build(String id){
		return MvcUriComponentsBuilder
		        .fromMethodName(WebHookController.class, "trigger", id)
		        .build().toString();
	}
}
