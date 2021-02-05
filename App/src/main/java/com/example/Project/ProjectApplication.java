package com.example.Project;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Project.init.InitData;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		
		ArrayList<JSONObject> allEvents = InitData.getJSON();
		InitData.getDatafromJson(allEvents);
	     
		
	
	}

}
