package com.example.Project.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.util.filters.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Classe con metodi per applicazione dei filtri
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class ApplyFilter {

	private final static String str = "com.example.Project.util.filters.Filter";

	public static ArrayList<Event> checkFilter(JSONObject filter, ArrayList<Event> eventsList) {

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> filterMap = mapper.convertValue(filter, HashMap.class);

		// Iterator<Entry<String, Object>> it = filterMap.entrySet().iterator();
		// Iterator<Entry<String, ArrayList<Event>>> it1 =
		// filteredEvents.entrySet().iterator();

		for (Map.Entry<String, Object> ent : filterMap.entrySet()) {
			ArrayList<Event> lastFiltered = applyFilter(eventsList, ent);
			eventsList = lastFiltered;
		}

		return eventsList;

	}

	public static ArrayList<Event> applyFilter(ArrayList<Event> eventsList, Map.Entry<String, Object> ent) {

		String cls = str.concat(ent.getKey());
		ArrayList<Event> lastFiltered = new ArrayList<Event>();

		try {
			Class<?> filterClass = Class.forName(cls);
			Constructor<?> ct = filterClass.getDeclaredConstructor();
			Filter filter = (Filter) ct.newInstance();

			for (Event e : eventsList) {

				if (filter.okFilter(e, (String) ent.getValue()))
					lastFiltered.add(e);

			}

		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lastFiltered;
	}

}
