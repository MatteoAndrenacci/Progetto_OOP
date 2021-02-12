package com.example.Project.util.other;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

public class StatsOperations {

	public static String filterDay = "{\r\n" + "\"DateDay\":\"x\"\r\n" + "}";

	public static int MaxEvents(ArrayList<Event> events, String stringDate) {

		ArrayList<Event> filteredByDay = new ArrayList<Event>();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		// filtro events per giorno
		// per ogni giorno conto quanti eventi
		// il numero che trovo lo metto su array e vedo qual è il max
		LocalDate date = Converter.dateConv(stringDate);
		int i = 0;
		int max = 0;
		try {
			while (i <= date.lengthOfMonth() - 1) {

				filteredByDay = ApplyFilter.checkFilter(
						(JSONObject) JSONValue.parseWithException(filterDay.replace("x", date.plusDays(i).toString())),
						events);
				numEvents.add(filteredByDay.size());
				i++;
			}

			for (Integer e = 0; e < numEvents.size(); e++) {
				if (numEvents.get(e) > max)
					max = numEvents.get(e);

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return max;

	}

	public static int MinEvents(ArrayList<Event> events, String stringDate) {

		ArrayList<Event> filteredByDay = new ArrayList<Event>();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		// filtro events per giorno
		// per ogni giorno conto quanti eventi
		// il numero che trovo lo metto su array e vedo qual è il max
		LocalDate date = Converter.dateConv(stringDate);
		int i = 0;
		int min = 0;
		try {
			while (i <= date.lengthOfMonth() - 1) {

				filteredByDay = ApplyFilter.checkFilter(
						(JSONObject) JSONValue.parseWithException(filterDay.replace("x", date.plusDays(i).toString())),
						events);
				numEvents.add(filteredByDay.size());
				i++;
			}

			for (Integer e = 0; e < numEvents.size(); e++) {
				if (numEvents.get(e) < min)
					min = numEvents.get(e);

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return min;

	}

	public static int AverageEvents(ArrayList<Event> events, String stringDate) {
		ArrayList<Event> filteredByDay = new ArrayList<Event>();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		// filtro events per giorno
		// per ogni giorno conto quanti eventi
		// il numero che trovo lo metto su array e vedo qual è il max
		LocalDate date = Converter.dateConv(stringDate);
		int i = 0;
		int avg = 0;
		int sum = 0;
		try {
			while (i <= date.lengthOfMonth() - 1) {

				filteredByDay = ApplyFilter.checkFilter(
						(JSONObject) JSONValue.parseWithException(filterDay.replace("x", date.plusDays(i).toString())),
						events);
				numEvents.add(filteredByDay.size());
				i++;
			}

			for (Integer e = 0; e < numEvents.size(); e++) {
				sum = sum + numEvents.get(e);

			}

			avg = sum / numEvents.size();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return avg;

	}

}
