package com.github.cheergoivan.fenkins.web.webhook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Component
public class WebHookBuilder {
	
	public String build(String id){
		return MvcUriComponentsBuilder
		        .fromMethodName(WebHookController.class, "trigger", id)
		        .build().toString();
	}
}
