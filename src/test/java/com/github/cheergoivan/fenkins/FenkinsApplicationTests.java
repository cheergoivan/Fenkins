package com.github.cheergoivan.fenkins;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.service.phase.Phase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FenkinsApplicationTests {

	@Test
	public void contextLoads() {
		Yaml yaml = new Yaml();
		Settings test = yaml.loadAs(this.getClass().getResourceAsStream("/settings.yml"), Settings.class);
		System.out.println(test);
		
		Phase phase = new Phase1().next(new Phase2()).next(new Phase3());
		phase.execute();
	}

}
