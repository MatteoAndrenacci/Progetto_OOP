package com.example.Project.util.filters;

import com.example.Project.model.Event;

public interface Filter {
	
	public boolean okFilter(Event e,String param);

}
