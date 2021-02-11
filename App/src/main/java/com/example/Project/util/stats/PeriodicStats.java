package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

public class PeriodicStats extends Stats{
	
	
	public static HashMap<String ,Object> getMonthStats(String month) {
	     HashMap<String ,Object> statesMap = getStatesMap();  //ottiene mappa di tutti gli stati con valori associati nulli

			try {
				for (Map.Entry<String, Object> ent : statesMap.entrySet()) {

					ApplyFilter.applyFilter(eventsList, ent); //filtro per stato
					ArrayList<Event> filteredByMonth = ApplyFilter      //filtro ulteriormente per il mese passato come parametro
							.checkFilter((JSONObject) JSONValue.parseWithException(filterMonth.replace("x", month)),ApplyFilter.filteredEvents);
				
							
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return statesMap;
		}

}
