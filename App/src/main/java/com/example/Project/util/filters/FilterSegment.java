package com.example.Project.util.filters;

import com.example.Project.model.Event;

/**
 * Classe che implementa Filter per filtrare per genere.
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class FilterSegment implements Filter {

	/**
	 * Valuta se il genere dell'evento coincide con il genere passato come parametro.
	 * @param e evento considerato
	 * @param param parametro del filtro
	 * @return boolean true se i valori coincidono , false altrimenti
	 * 
	 */

	public boolean okFilter( Event e, String param) {
		if ((e.getSegment()).equals(param))
			return true;
		return false;
	}

}
