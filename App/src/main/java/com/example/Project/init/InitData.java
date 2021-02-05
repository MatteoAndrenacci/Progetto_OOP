package com.example.Project.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Classe contentente metodi per inizializzazione dataset
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class InitData {

	/*
	 * Metodo con cui estraggo oggetto json da url dato.
	 * 
	 * @return allEvents array di tutti gli oggetti json contenuti in ogni pagina
	 * del dataset
	 *
	 */

	public static ArrayList<JSONObject> getJSON() {
		final String url = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=EB0C06RFdsBXXH4XHZuEx6j1ykM6yeVh&countryCode=CA&source=ticketmaster";
		ArrayList<JSONObject> allEvents = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();

		/*
		 * scorro tutte le pagine del dataset per ottenere l'intera lista di eventi che
		 * avranno luogo in Canada
		 */

		for (int i = 0; i < 50; i++) {

			try {
				String newURL = url.concat("&page=" + i);
				URLConnection openConnection = new URL(newURL).openConnection();
				InputStream in = openConnection.getInputStream();

				String data = "";
				String line = "";
				try {
					InputStreamReader inR = new InputStreamReader(in);
					BufferedReader buf = new BufferedReader(inR);

					while ((line = buf.readLine()) != null) {
						data += line;
						System.out.println(line);
					}
				} finally {
					in.close();
				}

				obj = (JSONObject) JSONValue.parseWithException(data);
				allEvents.add(obj);

			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return allEvents;
	}
	
	
	/*
	 * Estrapolazione dagli oggetti json delle info utili . 
	 * Le info estratte sono passate come parametri per creazione oggetti Event
	 * @params allEvents array oggetti json
	 * @return eventsList array di oggetti Event
	 * del dataset
	 *
	 */
	
	public static ArrayList<Event> getDatafromJson(ArrayList<JSONObject> allEvents) {

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

				Event newEvent = new Event(name, segment, Converter.dateConv(date), venue, city, state, country);
				eventsList.add(newEvent);
                
			}

		}
		return eventsList;
	}
}
