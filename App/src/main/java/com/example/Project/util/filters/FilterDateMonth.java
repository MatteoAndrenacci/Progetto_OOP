package com.example.Project.util.filters;

import com.example.Project.model.Event;

public class FilterDateMonth implements Filter{
	
	
	public boolean okFilter (Event e,Object param) {
		if((Object)e.getDate().getMonthValue()==param)
			return true;
		else return false;
	}

}
