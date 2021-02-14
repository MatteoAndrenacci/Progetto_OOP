package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;
import com.example.Project.util.other.StatsOperations;

public class PeriodicStats extends Stats {

	public static HashMap<String, Object> getMonthStats(JSONObject jsonMonth) {

		HashMap<String, Object> statesMap = getStatesMap(); // ottiene mappa di tutti gli stati con valori associati
															// nulli
		ArrayList<Event> filtered = new ArrayList<Event>();
		String month = ((String) jsonMonth.get("DateMonth")).concat("-01");

		HashMap<String, Integer> subMap = new HashMap<String, Integer>();
		
		
	
		try {

			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				
				String filterStateAndMonth = "{\r\n" + "\"State\":\" x \",\r\n" + "\"DateMonth\":\" y \"\r\n" + "}";

				filtered = ApplyFilter.checkFilter(
						(JSONObject) JSONValue
								.parseWithException(filterStateAndMonth.replace("x", ent.getKey()).replace("y", month)),
						eventsList);
				

				subMap.put("Massimo", StatsOperations.MaxEvents(filtered, month));
				subMap.put("Minimo", StatsOperations.MinEvents(filtered, month));
				subMap.put("Media", StatsOperations.AverageEvents(filtered, month));
				ent.setValue(subMap);

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statesMap;
	}

}
