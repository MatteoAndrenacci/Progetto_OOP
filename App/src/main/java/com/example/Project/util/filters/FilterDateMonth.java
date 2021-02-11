package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterDateMonth implements Filter{
	
	
	public boolean okFilter (Event e,String param) {
		if(e.getDate().contains(param))
			return true;
		else return false;
	}

}
