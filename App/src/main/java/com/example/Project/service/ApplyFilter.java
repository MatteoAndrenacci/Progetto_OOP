package com.example.Project.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.model.Event;
import com.example.Project.util.filters.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe con metodi per applicazione dei filtri
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class ApplyFilter {

	private final static String str = "com.example.Project.util.filters.Filter";
	

    /**
     * Controlla il tipo di filtro richiesto
     * 
     * @param filter filtro da applicare, in formato json
     * @param eventsList lista di tutti gli eventi da filtrare 
     * @return ArrayList<Event> array di eventi filtrati
     * @throws InvalidParameterException
     * @throws InvalidFormatException
     */
	public static ArrayList<Event> checkFilter(JSONObject filter, ArrayList<Event> eventsList)
			throws InvalidParameterException, InvalidFormatException {

		

		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> filterMap = mapper.convertValue(filter, HashMap.class);
		
		if (filterMap.isEmpty())
			throw new InvalidParameterException("Il filtro non può essere vuoto");

		for (Map.Entry<String, Object> ent : filterMap.entrySet()) {
			
			if (((ArrayList<String>) ent.getValue()).size() < 1)
				throw new InvalidParameterException("I parametri del filtro non possono essere vuoti");
			
			if (!ent.getKey().equals("DateDay") && !ent.getKey().equals("DateMonth") && !ent.getKey().equals("DateYear") && !ent.getKey().equals("Segment") && !ent.getKey().equals("State"))
				throw new InvalidFormatException("Il campo su cui effettuare il filtraggio non è ammesso. Ammessi : DateDay, DateMonth, DateYear, Segment, State");
			
			if (!(ent.getValue() instanceof ArrayList<?>))
				throw new InvalidFormatException("I parametri devono essere degli array");
			
			
			
			
			ArrayList<Event> lastFiltered = applyFilter(eventsList, ent);
			eventsList = lastFiltered;
			
		}

		return eventsList;

	}
	
	/**
	 * Applica il filtro 
	 * @param eventsList
	 * @param ent coppia chiave-valore contenente rispettivamente campo e parametro 
	 * su cui effettuare filtraggio
	 * @return ArrayList<Event> array di eventi filtrati
	 */
	public static ArrayList<Event> applyFilter(ArrayList<Event> eventsList, Map.Entry<String, Object> ent) {

		String cls = str.concat(ent.getKey());
		ArrayList<Event> lastFiltered = new ArrayList<Event>();

		try {
			Class<?> filterClass = Class.forName(cls);
			Constructor<?> ct = filterClass.getDeclaredConstructor();
			Filter filter = (Filter) ct.newInstance();

			for (Event e : eventsList) {

				for (String s : (ArrayList<String>) ent.getValue()) {

					if (filter.okFilter(e, s))
						lastFiltered.add(e);
				}

			}

		} catch (SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | ClassNotFoundException | InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return lastFiltered;
	}

}
