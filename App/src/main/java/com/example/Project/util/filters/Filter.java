package com.example.Project.util.filters;

import com.example.Project.model.Event;


/**
 * Interfaccia che ogni filtro implementa
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public interface Filter {
	
	/**
	 * Metodo astratto 
	 * @param e evento da valutare
	 * @param param parametro su cui effettuare la valutazione
	 * @return boolean true se l'evento soddisfa le condizioni del filtro, false altrimenti
	 */
	
	public boolean okFilter(Event e,String param);

}
