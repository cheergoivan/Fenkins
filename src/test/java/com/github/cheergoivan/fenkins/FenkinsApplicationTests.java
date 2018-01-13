package com.github.cheergoivan.fenkins;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import com.github.cheergoivan.fenkins.entity.settings.FenkinsSettings;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FenkinsApplicationTests {

	@Test
	public void contextLoads() {
		Yaml yaml = new Yaml();
		InputStream in = this.getClass().getResourceAsStream("/test.yml");
		System.out.println(in == null);
		FenkinsSettings test = yaml.loadAs(this.getClass().getResourceAsStream("/test.yml"), FenkinsSettings.class);
		System.out.println(test);
	}

}
