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

	public static int MaxEvents(ArrayList<Event> events) {

		ArrayList<Event> filteredByDay = new ArrayList<Event>();
		ArrayList<Integer> numEvents = new ArrayList<Integer>();
		// filtro events per giorno
		// per ogni giorno conto quanti eventi
		// il numero che trovo lo metto su array e vedo qual è il max
		LocalDate date = Converter.dateConv(events.get(0).getDate());
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

			for (int e = 0; e < numEvents.size(); e++) {
				max = Integer.max(numEvents.get(e), numEvents.get(e++));

			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return max;

	}

}
