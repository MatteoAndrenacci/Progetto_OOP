package com.example.Project.util.filters;

import com.example.Project.model.Event;
/**
 * Classe che implementa Filter per filtrare per stato.
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class FilterState implements Filter {

	/**
	 * Valuta se lo stato in cui avviene l'evento coincide con lo stato passato come parametro.
	 * @param e evento considerato
	 * @param param parametro del filtro
	 * @return boolean true se i valori coincidono , false altrimenti
	 * 
	 */

	public boolean okFilter( Event e, String param) {
		if ((e.getState()).equals(param))
			return true;
		else
			return false;
	}

}
