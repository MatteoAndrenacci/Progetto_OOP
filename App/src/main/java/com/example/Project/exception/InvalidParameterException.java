package com.example.Project.exception;

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
