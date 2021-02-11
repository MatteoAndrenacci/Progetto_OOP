package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterState implements Filter {

	

	public boolean okFilter( Event e, String param) {
		if ((e.getState()).equals(param))
			return true;
		else
			return false;
	}

}
