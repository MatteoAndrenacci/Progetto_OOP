package com.example.Project.util.other;

import java.util.ArrayList;

/**
 * Classe contenente le operazioni utili all statistiche: 
 * Massimo, Minimo e Media.
 * 
 * @author matteoandrenacci
 * @author eleonorabrasili
 *
 */
public class StatsOperations {

	/**
	 * Individua il Massimo elemento dell'array passato come parametro
	 * @param numEvents array di interi 
	 * @return Integer massimo trovato
	 */

	public static Integer MaxEvents(ArrayList<Integer> numEvents) {

		int max = 0;
		for (Integer i : numEvents) {
			if (i > max)
				max = i;
		}

		return max;
	}

	/**
	 * Individua il Minimo elemento dell'array passato come parametro
	 * @param numEvents array di interi 
	 * @return Integer minimo trovato
	 */

	public static Integer MinEvents(ArrayList<Integer> numEvents) {

		int min = 0;
		for (Integer i : numEvents) {
			if (i < min)
				min = i;
		}

		return min;
	}
	
	/**
	 * Individua la Media tra tutti gli elementi dell'array passato come parametro
	 * @param numEvents array di interi 
	 * @return Integer media trovata
	 */
	public static Integer AverageEvents(ArrayList<Integer> numEvents) {

		int sum = 0;
		for (Integer i : numEvents) {
			sum += i;
		}

		return sum / 12;
	}

}
