package com.example.Project.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;
import com.example.Project.util.stats.NumericStats;
import com.example.Project.util.stats.PeriodicStats;

@RestController
public class Controller {
	
	
	@RequestMapping (value = "events", method = RequestMethod.GET)
	public ArrayList<Event> getEvents(){
		return InitData.getDatafromJson();
	}
	
	@RequestMapping (value = "filteredEvents", method = RequestMethod.POST)
	public ArrayList<Event> getFilteredEvents (@RequestBody JSONObject filter) throws InvalidParameterException, InvalidFormatException{
		 return ApplyFilter.checkFilter(filter,getEvents());
	}
	
	@RequestMapping (value = "stats", method = RequestMethod.GET)
	public HashMap<String, Object> getStats(){
		return NumericStats.getStatsForSegment();
	}
	
	@RequestMapping (value = "stats/month", method = RequestMethod.POST)
	public HashMap<String, Object> getStatsByMonth (@RequestBody JSONObject jsonMonth){
		 return PeriodicStats.getMonthStats(jsonMonth);
	}
	

}
