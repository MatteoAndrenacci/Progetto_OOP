package com.example.Project.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.util.filters.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApplyFilter {

	private static ArrayList<Event> eventsList = InitData.getDatafromJson();
	
	private final static String str = "com.example.Project.util.filters.Filter";
	
	public static ArrayList<Event> filteredEvents = new ArrayList<Event>();

	
	
	
	public static ArrayList<Event> checkFilter(JSONObject filter) {

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> filterMap = mapper.convertValue(filter, HashMap.class);

		for (Map.Entry<String, Object> ent : filterMap.entrySet()) {

			
			applyFilter(eventsList,ent);

			
		}
		
		return filteredEvents;

	}

	public static void applyFilter(ArrayList<Event> eventsList, Map.Entry<String, Object> ent) {

		
		String cls = str.concat(ent.getKey());
		
		try {
			Class<?> filterClass= Class.forName(cls);
			Constructor<?> ct = filterClass.getDeclaredConstructor();
			Filter filter = (Filter) ct.newInstance();
			

		for (Event e : eventsList) {
			
			if(filter.okFilter(e, ent.getValue()))
				filteredEvents.add(e);

		}
		
		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
