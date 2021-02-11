package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;

public class Stats {
	
	static ArrayList<Event> eventsList = InitData.getDatafromJson();
	static String filterState = "{\r\n" + "\"State\":\"x\"\r\n" + "}";
	static String filterSegment = "{\r\n" + "\"Segment\":\"x\"\r\n" + "}";
	static String filterMonth = "{\r\n" + "\"DateMonth\":\"x\"\r\n" + "}";
	
	
	// preparazione mappa di tutti gli stati in vista delle statistiche
	public static HashMap<String, Object> getStatesMap() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();
		for (Event e : eventsList) {

			statesMap.putIfAbsent(e.getState(), null);

		}

		return statesMap;
	}
	

}
