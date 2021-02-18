package com.example.Project.util.stats;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.Project.init.InitData;
import com.example.Project.model.Event;

/**
 * SuperClasse estesa da tutti i tipi di statistiche 
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class Stats {
	
	static ArrayList<Event> eventsList = InitData.getDatafromJson();
	
	/**
	 * Preparazione mappa di tutti gli stati in vista delle statistiche.
	 * Ad ogni stato associa il valore 'null'
	 * @return statesMap mappa che ad ogni stato associa valore 'null'
	 */
	
	public static HashMap<String, Object> getStatesMap() {
		HashMap<String, Object> statesMap = new HashMap<String, Object>();
		for (Event e : eventsList) {

			statesMap.putIfAbsent(e.getState(), null);

		}

		return statesMap;
	}
	

}
