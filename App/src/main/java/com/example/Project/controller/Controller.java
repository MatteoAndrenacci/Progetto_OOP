package com.example.Project.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;
import com.example.Project.util.stats.NumericStats;
import com.example.Project.util.stats.PeriodicStats;

/**
 * Classe sede del controller per l'applicazione,
 * contentente tutte le possibili rotte.
 * 
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

@RestController
public class Controller {
	
	/**
	 * Rotta per ottenere tutti gli eventi in programmazione
	 * 
	 * @return ArrayList<Event>
	 */
	
	@RequestMapping (value = "events", method = RequestMethod.GET)
	public ArrayList<Event> getEvents(){
		return InitData.getDatafromJson();
	}
	
	
	/**
	 * Rotta per ottenere eventi filtrati in base ad un parametro scelto
	 * 
	 * @param filter filtro richiesto 
	 * @return  ArrayList<Event> array degli eventi filtrati
	 * @throws InvalidParameterException
	 * @throws InvalidFormatException
	 */
	@RequestMapping (value = "filteredEvents", method = RequestMethod.POST)
	public ArrayList<Event> getFilteredEvents (@RequestBody JSONObject filter) throws InvalidParameterException, InvalidFormatException{
		 return ApplyFilter.checkFilter(filter,getEvents());
	}
	
	
	/**
	 * Rotta per ottenere delle statistiche sul numero di 
	 * eventi in ogni stato, raggruppati per genere.
	 * 
	 * @return HashMap<String, Object> mappa di tutti gli stati
	 *  con associati loro il numero di eventi per ogni genere.
	 */
	@RequestMapping (value = "stats", method = RequestMethod.GET)
	public HashMap<String, Object> getStats(){
		return NumericStats.getStatsForSegment();
	}
	
	/**
	 * Rotta per ottenere delle statistiche riguardo al numero 
	 * massimo, minimo e medio di eventi mensili.
	 * 
	 * @return HashMap<String, Object> mappa di tutti gli stati
	 *  con associati loro il numero massimo, minimo e medio di eventi mensili.
	 */
	
	@RequestMapping (value = "stats/month", method = RequestMethod.GET)
	public HashMap<String, Object> getStatsByMonth (){
		 return PeriodicStats.getMonthStats();
	}
	

}
