package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NumericStats extends Stats {

	


	// ottengo mappa numero di eventi per ogni stato
	public static HashMap<String, Integer> getNumStates(ArrayList<Event> events) {

		HashMap<String, Integer> numStates = new HashMap<String, Integer>();

		try {
			for (Event e : events)
				numStates.putIfAbsent(e.getState(), null);
			for (Map.Entry<String, Integer> ent : numStates.entrySet()) {
				String filterState = "{\r\n" + "\"State\":\"x\"\r\n" + "}";
				ent.setValue(ApplyFilter
						.checkFilter((JSONObject) JSONValue.parseWithException(filterState.replace("x", ent.getKey())),eventsList)
						.size());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numStates;
	}

	// ottengo mappa numero di eventi per ogni genere

	public static HashMap<String, Integer> getNumSegment(ArrayList<Event> events) {

		HashMap<String, Integer> numSegment = new HashMap<String, Integer>();
		String filterSegment = "{\r\n" + "\"Segment\":\"x\"\r\n" + "}";

		try {
			for (Event e : events)
				numSegment.putIfAbsent(e.getSegment(), null);
			for (Map.Entry<String, Integer> ent : numSegment.entrySet()) {
				ent.setValue(ApplyFilter
						.checkFilter(
								(JSONObject) JSONValue.parseWithException(filterSegment.replace("x", ent.getKey())),eventsList)
						.size());
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numSegment;
	}

	
	
	
	// ottengo mappa di numero eventi per ogni stato raggruppati per genere
	public static HashMap<String, Object> getStatsForSegment() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();
		String filterState = "{\r\n" + "\"State\":\"x\"\r\n" + "}";
		
		try {
			ListIterator<Event> it1 = eventsList.listIterator();
			while (it1.hasNext()) {

				String state = it1.next().getState();
				
				statesMap.putIfAbsent(state, getNumSegment(ApplyFilter
						.checkFilter((JSONObject) JSONValue.parseWithException(filterState.replace("x", state)),eventsList)));

			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return statesMap;
	}

}
