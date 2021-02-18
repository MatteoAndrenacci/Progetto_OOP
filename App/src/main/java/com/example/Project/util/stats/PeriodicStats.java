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

/**
 * Classe che estende Stats. Ottiene mappe contenti i numeri risultanti
 * da statistiche per periodo, per ogni stato
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class PeriodicStats extends Stats {

	/**
	 * Calcola il Massimo Minimo e Media di eventi su base annua, mensile 
	 * o giornaliera
	 *
	 * @param obj Json contenente il periodo su cui effettuare le statistiche
	 * @return statesMap mappa che ad ogni stato associa il numero massimo, minimo e medio di eventi
	 * in base al periodo passato come parametro
	 * @throws InvalidFormatException se il formato non è valido
	 * @throws InvalidParameterException se i parametri non sono validi
	 * @throws ParseException errore parsing
	 */
	public static HashMap<String, Object> getPerStats(JSONObject obj) throws InvalidFormatException, InvalidParameterException, ParseException{
		
		HashMap<String, Object> statesMap = getStatesMap();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		ArrayList<Event> filtered = new ArrayList<Event>();
		
		if(!obj.containsValue("DateMonth") && !obj.containsValue("DateYear") && !obj.containsValue("DateDay"))
			throw new InvalidFormatException("Campo su cui effettuare le statistiche non ammesso. Campi ammessi: DateMonth, DateYear, DateDay");
		
		
		String filter = "{\r\n" + "\"State\":[\"x\"],\r\n" + "\"Date\":[\"b\"]\r\n" + "}";
		String news = filter.replace( "Date", (CharSequence) obj.get("stats"));
		String news2 = new String();
		
		//stats mensili
		if (news.contains("DateMonth")) {
			//news2 = news.replace("b", "2021-01");
			
			LocalDate convDate = LocalDate.of(2021, 1, 1);
			
			for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				for(int i = 0; i < 24 ; i++) {
				    
					news2 = (news.replace("x", ent.getKey()));
					String dateString = convDate.plusMonths(i).toString();
					String news3 = news2.replace("b", dateString.substring(0,dateString.length()-3));
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
			news2 = news.replace("b", "2021");
            for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				
				String news3 = news2.replace("x", ent.getKey());
				filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news3), eventsList);
				numEvents.add(filtered.size());
                HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo annuale", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo annuale", StatsOperations.MinEvents(numEvents));
				subMap.put("Media annuale", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
			}
			
		}
		
		//stats giornaliere
		if(news.contains("DateDay")) {
			
			
			LocalDate convDate = LocalDate.of(2021,1,1);
            for (Map.Entry<String, Object> ent : statesMap.entrySet()) {
				
            	for(int i = 0; i < 365 ; i++) {
				news2 = (news.replace("x", ent.getKey()));
				String news3 = news2.replace("b", convDate.plusDays(i).toString());
				
				filtered = ApplyFilter.checkFilter((JSONObject) JSONValue.parseWithException(news3), eventsList);
				numEvents.add(filtered.size());

				}
                HashMap<String, Integer> subMap = new HashMap<String, Integer>();
				
				subMap.put("Massimo giornaliero", StatsOperations.MaxEvents(numEvents));
				subMap.put("Minimo giornaliero", StatsOperations.MinEvents(numEvents));
				subMap.put("Media giornaliera", StatsOperations.AverageEvents(numEvents));

				ent.setValue(subMap);	
					
            	
			}
			
		}
		
	
		return statesMap;
	}

}
