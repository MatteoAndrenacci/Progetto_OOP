package com.example.Project;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Project.init.InitData;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		
		JSONObject json = InitData.getJSON();
		InitData.getDatafromJson(json);
	     
		
	
	}

}
