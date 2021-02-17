package com.example.Project.util.other;

import java.util.ArrayList;

/*
 * 
 * 
 * 
 */
public class StatsOperations {

	/*
	 * 
	 * 
	 * 
	 */

	public static Integer MaxEvents(ArrayList<Integer> numEvents) {

		int max = 0;
		for (Integer i : numEvents) {
			if (i > max)
				max = i;
		}

		return max;
	}

	/*
	 * 
	 * 
	 * 
	 */

	public static Integer MinEvents(ArrayList<Integer> numEvents) {

		int min = 0;
		for (Integer i : numEvents) {
			if (i < min)
				min = i;
		}

		return min;
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	public static Integer AverageEvents(ArrayList<Integer> numEvents) {

		int sum = 0;
		for (Integer i : numEvents) {
			sum += i;
		}

		return sum / 12;
	}

}
