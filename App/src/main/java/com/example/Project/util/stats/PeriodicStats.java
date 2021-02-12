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

		HashMap<String, Integer> subMap = new HashMap<String, Integer>();
		ArrayList<Event> filteredByState = new ArrayList<Event>();
		ArrayList<Event> filteredByMonth = ApplyFilter.checkFilter(jsonMonth, eventsList);
		try {

			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {

				filteredByState = ApplyFilter.checkFilter(
						(JSONObject) JSONValue.parseWithException(filterState.replace("x", ent.getKey())),
						filteredByMonth);

				String month = ((String) jsonMonth.get("DateMonth")).concat("-01");
				subMap.put("Massimo", StatsOperations.MaxEvents(filteredByState, month));
				ent.setValue(subMap);

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statesMap;
	}

}
