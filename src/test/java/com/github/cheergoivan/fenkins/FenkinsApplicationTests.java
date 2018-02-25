package com.github.cheergoivan.fenkins;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.service.mail.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FenkinsApplicationTests {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private Settings settings;

	@Test
	public void contextLoads() {
		System.out.println(settings.getMail().getHost());
		mailService.sendMail("feastbooking@163.com", "xiweipen@sina.com", "FeastBooking 驗證碼", "您的验证码为：9388444，此验证码两分钟内有效。");
	}

}
