package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterState implements Filter {

	

	public boolean okFilter( Event e, Object param) {
		if (e.getState() == param)
			return true;
		else
			return false;
	}

}
