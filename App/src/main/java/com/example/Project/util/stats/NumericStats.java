package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

/**
 * Classe che estende Stats. Ottiene mappe contenti i numeri risultanti
 * da statistiche per ogni stato.
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class NumericStats extends Stats {

	/**
	 * Calcola il numero di eventi per ogni stato
	 * 
	 * @param events array di eventi 
	 * @return  numStates mappa di tutti gli stati con associati i rispettivi numeri 
	 */
	public static HashMap<String, Integer> getNumStates(ArrayList<Event> events) {

		HashMap<String, Integer> numStates = new HashMap<String, Integer>();

		try {
			for (Event e : events)
				numStates.putIfAbsent(e.getState(), null);
			for (Map.Entry<String, Integer> ent : numStates.entrySet()) {
				String filterState = "{\r\n" + "\"State\":[\"x\"]\r\n" + "}";
				ent.setValue(ApplyFilter
						.checkFilter((JSONObject) JSONValue.parseWithException(filterState.replace("x", ent.getKey())),
								eventsList)
						.size());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException | InvalidFormatException e) {

			e.getMessage();

		}
		return numStates;
	}

    /**
     * Calcola numero eventi per ogni genere
     * @param events array di eventi
     * @return numSegment mappa di tutti i generi con associati i rispettivi numeri
     */

	public static HashMap<String, Integer> getNumSegment(ArrayList<Event> events) {

		HashMap<String, Integer> numSegment = new HashMap<String, Integer>();

		try {
			for (Event e : events)
				numSegment.putIfAbsent(e.getSegment(), null);
			for (Map.Entry<String, Integer> ent : numSegment.entrySet()) {
				String filterSegment = "{\r\n" + "\"Segment\":[\"x\"]\r\n" + "}";
				ent.setValue(ApplyFilter.checkFilter(
						(JSONObject) JSONValue.parseWithException(filterSegment.replace("x", ent.getKey())), events)
						.size());
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException | InvalidFormatException e) {

			e.getMessage();

		}
		return numSegment;
	}
	
	/**
	 * Calcola il numero di eventi per ogni stato, raggruppati per genere
	 * 
	 * @return statesMap mappa di tutti gli stati con associati i
	 * rispettivi numeri, raggruppati per genere.
	 */

	public static HashMap<String, Object> getStatsForSegment() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();
		String filterState = "{\r\n" + "\"State\":[\"x\"]\r\n" + "}";

		try {
			ListIterator<Event> it1 = eventsList.listIterator();
			while (it1.hasNext()) {

				String state = it1.next().getState();

				statesMap.putIfAbsent(state,
						getNumSegment(ApplyFilter.checkFilter(
								(JSONObject) JSONValue.parseWithException(filterState.replace("x", state)),
								eventsList)));

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException | InvalidFormatException e) {

			e.getMessage();

		}

		return statesMap;
	}

}
