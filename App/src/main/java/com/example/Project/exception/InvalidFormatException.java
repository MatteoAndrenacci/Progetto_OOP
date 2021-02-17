package com.example.Project.exception;


/**
 * Eccezione lanciata se il formato del filtro non è corretto
 *
 * @author matteoandrenacci
 * @author eleonorabrasili
 */
public class InvalidFormatException extends Exception {
	
	public  InvalidFormatException () {
		super();
		System.out.println("Formato non valido ...");
	}
	
	
	public  InvalidFormatException (String msg) {
		super(msg);
		System.out.println("Formato non valido ..." + msg);
		
	}

}
