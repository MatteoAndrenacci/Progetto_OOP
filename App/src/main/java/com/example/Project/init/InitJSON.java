package com.example.Project.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class InitJSON {

	/**
	 * Metodo con cui estraggo oggetto json da url dato.
	 * 
	 * @return allEvents array di tutti gli oggetti json contenuti in ogni pagina
	 *         del dataset
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

}
