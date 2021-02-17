package com.example.Project.init;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.example.Project.model.Event;

/*
 * Classe contentente metodi per inizializzazione dataset
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class InitData {

	/*
	 * Estrapolazione dagli oggetti json delle info utili . Le info estratte sono
	 * passate come parametri per creazione oggetti Event
	 * 
	 * @params allEvents array oggetti json
	 * 
	 * @return eventsList array di oggetti Event del dataset
	 *
	 */

	public static ArrayList<Event> getDatafromJson() {
		ArrayList<JSONObject> allEvents = InitJSON.getJSON();
		String name;
		String segment;
		String date;
		String venue;
		String city;
		String state;
		String country;

		ArrayList<JSONObject> temp = new ArrayList<JSONObject>(); // oggetti di appoggio per estrapolazioni delle info.
		JSONObject c = new JSONObject(); // Diventano garbage dopo aver ottenuto ogni singola info utile.

		ArrayList<Event> eventsList = new ArrayList<Event>();// array che conterrà tutti gli oggetti Event ottenuti alla
																// fine delle iterazioni

		for (int i = 0; i < 50; i++) {

			JSONObject ob = (JSONObject) (allEvents.get(i).get("_embedded"));
			ArrayList<JSONObject> events = (ArrayList<JSONObject>) ob.get("events");

			for (JSONObject o : events) {
				// estraggo valore "name"
				name = (String) o.get("name");
				// estraggo valore "segment"
				temp = (ArrayList<JSONObject>) o.get("classifications");
				c = (JSONObject) (temp.get(0)).get("segment");
				segment = (String) c.get("name");

				// estraggo valore "date"
				c = (JSONObject) ((HashMap) o.get("dates")).get("start");
				date = (String) ((HashMap) c).get("localDate");

				// estraggo valore "venue"
				c = (JSONObject) o.get("_embedded");
				temp = (ArrayList<JSONObject>) c.get("venues");
				venue = (String) (temp.get(0)).get("name");

				// estraggo valore "city"
				city = (String) ((HashMap) temp.get(0).get("city")).get("name");

				// estraggo valore "state"
				state = (String) ((HashMap) temp.get(0).get("state")).get("name");

				// estraggo valore "country"
				country = (String) ((HashMap) temp.get(0).get("country")).get("name");

				Event newEvent = new Event(name, segment, date, venue, city, state, country);
				eventsList.add(newEvent);

			}

		}
		return eventsList;
	}
}
