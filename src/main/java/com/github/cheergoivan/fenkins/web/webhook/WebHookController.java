package com.github.cheergoivan.fenkins.web.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.cheergoivan.fenkins.entity.settings.project.Project;
import com.github.cheergoivan.fenkins.service.mail.MailService;
import com.github.cheergoivan.fenkins.service.project.ProjectService;
import com.github.cheergoivan.fenkins.service.task.ProjectExecutorService;
import com.github.cheergoivan.fenkins.web.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/webhooks")
public class WebHookController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectExecutorService executor;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/{id}")
	public void trigger(@PathVariable String id) {
		Project project = projectService.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		executor.dispatch(project);
	}
	
	@PostMapping("/mails")
	public void postMails() {
		mailService.sendMail("feastbooking@163.com", "cheergoivan@163.com", "FeastBooking 驗證碼", "您的驗證碼為：556783  此驗證碼2分鐘内有效。");
	}
	
}
