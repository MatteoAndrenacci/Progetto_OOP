package com.example.Project.util.other;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*
 * Classe per conversione di una data da oggetto di tipo String 
 * ad oggetto di tipo LocalDateTime
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class Converter {

	public static LocalDate dateConv(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CANADA);
		LocalDate newDate = LocalDate.parse(date, formatter);

		return newDate;

	}

}
