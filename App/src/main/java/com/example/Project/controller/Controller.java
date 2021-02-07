package com.example.Project.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

@RestController
public class Controller {
	
	@RequestMapping (value = "filteredEvents", method = RequestMethod.POST)
	public ArrayList<Event> getFilteredEvents (@RequestBody JSONObject filter){
		 return ApplyFilter.checkFilter(filter);
	}
	
	
	

}
