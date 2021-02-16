package com.example.Project.exception;

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
