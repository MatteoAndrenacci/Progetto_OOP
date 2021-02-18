package com.example.Project.util.filters;

import java.time.LocalDate;

import com.example.Project.model.Event;
import com.example.Project.util.other.Converter;

/**
 * Classe che implementa Filter per filtrare per giorno.
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class FilterDateDay implements Filter {
	
	/**
	 * Converte la data passata come string in oggetto LocalDate.
	 * Valuta se il giorno dell'evento coincide con il giorno passato come parametro.
	 * @param e evento considerato
	 * @param param parametro del filtro
	 * @return boolean true se le date coincidono , false altrimenti
	 * 
	 */
	public boolean okFilter (Event e,String param) {
		LocalDate newDate = Converter.dateConv(e.getDate());
		LocalDate paramDate = Converter.dateConv(param);
		if(newDate.equals(paramDate))
			return true;
		else return false;
	}

}
