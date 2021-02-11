package com.example.Project.model;



public class Event {

	String name;
	String segment;
	String date;
	String venue;
	String city;
	String state;
	String country;
	
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
