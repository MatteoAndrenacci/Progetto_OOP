package com.example.Project.util.stats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;
import com.example.Project.util.other.StatsOperations;

/*
 * 
 * 
 * 
 */
public class PeriodicStats extends Stats {

	/*
	 * 
	 * 
	 * 
	 */

	
	public static HashMap<String, Object> getMonthStats() {

		HashMap<String, Object> statesMap = getStatesMap();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		ArrayList<Event> filtered = new ArrayList<Event>();
		String date = "2021-01";
		
		try {
			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				for (int i = 0; i < 12; i++) {
					String filterStateAndMonth = "{\r\n" + "\"State\":[\"x\"],\r\n" + "\"DateMonth\":[\"y\"]\r\n" + "}";
					
					String d = new String(date);
					if(i>=10) d = date.replace("-01", "-"+i);
					else if (i>0 && i<10)d = date.replace("-01", "-0"+i);
					
				    
					String news = (filterStateAndMonth.replace("x", ent.getKey())).replace("y",d);

					filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news), eventsList);
					numEvents.add(filtered.size());
				}
				
				HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo mensile", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo mensile", StatsOperations.MinEvents(numEvents));
				subMap.put("Media mensile", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
					
					
				}

			
		} catch (InvalidParameterException | InvalidFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return statesMap;

	}
	
	public static HashMap<String, Object> getPerStats(JSONObject obj) throws InvalidFormatException, InvalidParameterException, ParseException{
		
		HashMap<String, Object> statesMap = getStatesMap();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		ArrayList<Event> filtered = new ArrayList<Event>();
		
		if(!obj.containsValue("DateMonth") && !obj.containsValue("DateYear") && !obj.containsValue("DateDay"))
			throw new InvalidFormatException("Campo su cui effettuare le statistiche non ammesso. Campi ammessi: DateMonth, DateYear, DateDay");
		
		
		String filter = "{\r\n" + "\"State\":[\"x\"],\r\n" + "\"Date\":[\"y\"]\r\n" + "}";
		String news = filter.replace( "Date", (CharSequence) obj.get("stats"));
		String news2 = new String();
		
		//stats mensili
		if (news.contains("DateMonth")) {
			news2 = news.replace("y", "2021-01");
			
			LocalDate convDate = LocalDate.of(2021, 1, 1);
			
			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				for(int i = 0; i < 365 ; i++) {
				    
					news2 = (news.replace("x", ent.getKey()));
					String dateString = convDate.plusMonths(i).toString();
					String news3 = news2.replace("y", dateString.substring(dateString.length()-3));
				filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news3), eventsList);
				numEvents.add(filtered.size());
				}
                HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo mensile", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo mensile", StatsOperations.MinEvents(numEvents));
				subMap.put("Media mensile", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
			}

		}
		
		//stats annuali
		if(news.contains("DateYear")) {
			news2 = news.replace("y", "2021");
            for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				
				String news3 = news2.replace("x", ent.getKey());
				filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news3), eventsList);
				numEvents.add(filtered.size());
                HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo mensile", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo mensile", StatsOperations.MinEvents(numEvents));
				subMap.put("Media mensile", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
			}
			
		}
		
		//stats giornaliere
		if(news.contains("DateDay")) {
			
			
			LocalDate convDate = LocalDate.of(2021,1,1);
            for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				
            	for(int i = 0; i < 365 ; i++) {
				news2 = (news.replace("x", ent.getKey()));
				String news3 = news2.replace("y", convDate.plusDays(i).toString());
				filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news3), eventsList);
				numEvents.add(filtered.size());

				}
                HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo mensile", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo mensile", StatsOperations.MinEvents(numEvents));
				subMap.put("Media mensile", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
					
            	
			}
			
		}
		
	
		return statesMap;
	}

}
