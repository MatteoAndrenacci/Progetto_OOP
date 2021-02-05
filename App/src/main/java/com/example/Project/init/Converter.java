package com.example.Project.init;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Converter {

	public static LocalDateTime dateConv(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CANADA);
		LocalDateTime newDate = LocalDateTime.parse(date, formatter);

		return newDate;

	}

}
