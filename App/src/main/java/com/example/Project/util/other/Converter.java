package com.example.Project.util.other;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe per conversione di una data da oggetto di tipo String 
 * ad oggetto di tipo LocalDate
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class Converter {
	/**
	 * Converte tramite DateTimeFormatter la data passata come stringa in 
	 * un oggetto di tipo LocalDate.
	 * @param date stringa della data considerata
	 * @return LocalDate data convertita
	 */

	public static LocalDate dateConv(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CANADA);
		LocalDate newDate = LocalDate.parse(date, formatter);

		return newDate;

	}
	
	
}
