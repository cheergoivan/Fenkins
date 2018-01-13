package com.github.cheergoivan.fenkins;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import com.github.cheergoivan.fenkins.entity.settings.GlobalSettings;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FenkinsApplicationTests {

	@Test
	public void contextLoads() {
		Yaml yaml = new Yaml();
		GlobalSettings test = yaml.loadAs(this.getClass().getResourceAsStream("/settings.yml"), GlobalSettings.class);
		System.out.println(test);
	}

}
