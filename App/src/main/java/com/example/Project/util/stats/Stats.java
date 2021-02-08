package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

public class Stats {

	public static ArrayList<Event> eventsList = InitData.getDatafromJson();
	public static String filterState = "{\r\n" + "\"State\":\"x\"\r\n" + "}";
	public static String filterSegment = "{\r\n" + "\"Segment\":\"x\"\r\n" + "}";
	public static HashMap<String, Integer> statesMap = new HashMap<String, Integer>();

	
	//ottengo lista di tutti gli stati
	public static HashMap<String, Object> getStatesList() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();

		for (Event e : eventsList)
			statesMap.putIfAbsent(e.getState(), null);
		return statesMap;

	}
    
	
	//ottengo numero eventi corrispondenti ad una certa key
	public static HashMap<String, Integer> getStats(String key) {

		if (statesMap.containsKey(key))
			statesMap.replace(key, (statesMap.get(key)) + 1);
		else
			statesMap.put(key, 1);

		return statesMap;
	}

	
	//ottengo mappa di numero eventi per ogni stato raggruppati per genere
	public static HashMap<String, Object> getStatsForSegment() {
		HashMap<String, Object> statesMap = getStatesList();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		try {

			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {

				ArrayList<Event> filteredByState = ApplyFilter
						.checkFilter((JSONObject) JSONValue.parseWithException(filterState.replace("x", ent.getKey())));
				for (Event e : filteredByState) {
					map = getStats(e.getSegment());
				}

				ent.setValue(map);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statesMap;
	}

}
