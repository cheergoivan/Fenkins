package com.github.cheergoivan.fenkins.web.project;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cheergoivan.fenkins.dto.project.ProjectWebHookDTO;
import com.github.cheergoivan.fenkins.service.project.ProjectService;
import com.github.cheergoivan.fenkins.web.webhook.WebHookBuilder;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private WebHookBuilder webHookBuilder;

	@GetMapping
	public List<ProjectWebHookDTO> listProjects() {
		return projectService.listProjects().stream()
				.map(p -> new ProjectWebHookDTO(p.getName(), webHookBuilder.build(p.getId()))).collect(Collectors.toList());
	}
}
