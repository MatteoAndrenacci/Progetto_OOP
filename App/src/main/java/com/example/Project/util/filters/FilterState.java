package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterState implements Filter {

	

	public boolean okFilter( Event e, Object param) {
		if (((Object)e.getState()).equals(param))
			return true;
		else
			return false;
	}

}
