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

public class Stats {

	public static ArrayList<Event> eventsList = InitData.getDatafromJson();
	public static String filterState = "{\r\n" + "\"State\":\"x\"\r\n" + "}";
	public static String filterSegment = "{\r\n" + "\"Segment\":\"x\"\r\n" + "}";

	// ottengo mappa di tutti gli stati
	public static HashMap<String, Object> getStatesMap() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();

		for (Event e : eventsList)
			statesMap.putIfAbsent(e.getState(), null);
		return statesMap;

	}

	// ottengo mappa numero di eventi per stato
	public static HashMap<String, Integer> getNumStates(ArrayList<Event> events) {

		HashMap<String, Integer> numStates = new HashMap<String, Integer>();
		

		try {
			ListIterator<Event> it = events.listIterator();
			while (it.hasNext()) {

				String state = it.next().getState();
				numStates.putIfAbsent(state, ApplyFilter.checkFilter(
							(JSONObject) JSONValue.parseWithException(filterState.replace("x", state))).size());
			

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numStates;
	}

	// ottengo mappa numero di eventi per genere

	public static HashMap<String, Integer> getNumSegment(ArrayList<Event> events) {

		HashMap<String, Integer> numSegment = new HashMap<String, Integer>();
		
		

		try {
			for (Event e : events)
				numSegment.putIfAbsent(e.getSegment(), null);
			for(Map.Entry <String,Integer> ent : numSegment.entrySet()) {
				ent.setValue(ApplyFilter.checkFilter(
							(JSONObject) JSONValue.parseWithException(filterSegment.replace("x", ent.getKey()))).size());
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
		
		try {
			ListIterator<Event> it1 = eventsList.listIterator();
			while (it1.hasNext()) {

				String state = it1.next().getState();
				
				statesMap.putIfAbsent(state, getNumSegment(ApplyFilter
						.checkFilter((JSONObject) JSONValue.parseWithException(filterState.replace("x", state)))));

			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return statesMap;
	}

}
