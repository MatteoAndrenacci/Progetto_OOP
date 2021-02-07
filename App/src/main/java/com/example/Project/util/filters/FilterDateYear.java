package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterDateYear implements Filter{
	
	
	public boolean okFilter ( Event e,Object param) {
		if((Object)e.getDate().getYear()==param)
			return true;
		else return false;
	}

}
