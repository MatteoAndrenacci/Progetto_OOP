package com.example.Project.model;

/**
 * Modello per ogni evento in programmazione su TicketMaster
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class Event {
	
	
    /**
     * Nome dell'evento
     */
	private String name;
	/**
     * Genere dell'evento
     */
	private String segment;
	/**
     * Data dell'evento
     */
	private String date;
	/**
     * Luogo dell'evento
     */
	private String venue;
	/**
     * Città in cui si terrà l'evento
     */
	private String city;
	/**
     * Stato in cui si terrà l'evento
     */
	private String state;
	/**
     * Paese in cui si terrà l'evento
     */
	private String country;
	
	
	/**
	 * Costruttore con parametri
	 * @param name Nome dell'evento
	 * @param segment Genere dell'evento
	 * @param date Data dell'evento
	 * @param venue Luogo dell'evento
	 * @param city Città in cui si terrà l'evento
	 * @param state Stato in cui si terrà l'evento
	 * @param country Paese in cui si terrà l'evento
	 */
	public Event(String name, String segment, String date, String venue, String city, String state, String country) {
		super();
		this.name = name;
		this.segment = segment;
		this.date = date;
		this.venue = venue;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
}
