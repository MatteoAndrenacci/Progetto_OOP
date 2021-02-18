package com.example.Project.util.filters;

import java.time.LocalDate;

import com.example.Project.model.Event;
import com.example.Project.util.other.Converter;

/**
 * Classe che implementa Filter per filtrare per anno.
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class FilterDateYear implements Filter{
	/**
	 * Converte la data passata come string in oggetto LocalDate.
	 * Valuta se l'anno dell'evento coincide con l'anno passato come parametro.
	 * @param e evento considerato
	 * @param param parametro del filtro
	 * @return boolean true se le date coincidono , false altrimenti
	 * 
	 */
	
	public boolean okFilter ( Event e,String param) {
		String newParam = param.concat("-01-01");
		LocalDate newDate = Converter.dateConv(e.getDate());
		LocalDate paramDate = Converter.dateConv(newParam);
		if(newDate.getYear() == paramDate.getYear())
			return true;
		else return false;
	}

}
