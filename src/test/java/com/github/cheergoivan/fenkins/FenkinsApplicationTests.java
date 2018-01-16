package com.github.cheergoivan.fenkins;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import com.github.cheergoivan.fenkins.entity.project.ProjectId;
import com.github.cheergoivan.fenkins.entity.settings.Settings;
import com.github.cheergoivan.fenkins.service.phase.Phase;
import com.github.cheergoivan.fenkins.util.serialization.SerializationUtils;

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

		File file = new File("projects");
		System.out.println(file.getAbsolutePath());
		
		try {
			SerializationUtils.write(new ProjectId("2", "project2"), 
					file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			List<ProjectId> projects = SerializationUtils.readAll(file);
			System.out.println(projects.size());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
