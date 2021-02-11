package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterSegment implements Filter {

	

	public boolean okFilter( Event e, String param) {
		if ((e.getSegment()).equals(param))
			return true;
		return false;
	}

}
