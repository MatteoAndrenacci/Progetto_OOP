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

public class InitData {

	public static ArrayList<JSONObject> getJSON() {
		final String url = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=EB0C06RFdsBXXH4XHZuEx6j1ykM6yeVh&countryCode=CA&source=ticketmaster";
		ArrayList <JSONObject> allEvents = new ArrayList<JSONObject>;
		JSONObject obj = new JSONObject();
		for (int i = 0;i<50;i++) {
		
		try {
            String newURL = url.concat("&page="+ i);
			URLConnection openConnection = new URL(newURL).openConnection();
			// openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1;
			// WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
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

	public static void getDatafromJson(ArrayList<JSONObject> allEvents) {
		
		String name;
		String segment;
		String date;
		String venue;
		String city;
		String state;
		String country;
		
		/*bisogna cambiare e lavorare invece che con oggetto JSON con array di oggetti Json
		
		JSONObject ob = (JSONObject) obj.get("_embedded");
		
		ArrayList <JSONObject> events = (ArrayList<JSONObject>) ob.get("events");
		
		for (JSONObject o : events) {
			ArrayList<JSONObject> classifications = (ArrayList<JSONObject>) o.get("classifications");
			JSONObject c = (JSONObject) (classifications.get(0)).get("segment");
			name = (String) c.get("name");
			Object d = ((HashMap) o.get("dates")).get("start");
			date = (String)((HashMap) d).get("localDate");
			System.out.println(name);
			System.out.println(date);
			 
			
		}
		
		
		
	*/	
	}
}
