package com.example.Project.exception;


/**
 * Eccezione lanciata se i parametri del filtro non sono 
 * validi
 * 
 * @author matteoandrenacci
 * @author eleonorabrasili
 *
 */
public class InvalidParameterException extends Exception {
	
	public  InvalidParameterException () {
		super();
		System.out.println("Parametro non valido ...");
	}
	
	
	public  InvalidParameterException (String msg) {
		super(msg);
		System.out.println("Parametro non valido ..." + msg);
		
	}

}
