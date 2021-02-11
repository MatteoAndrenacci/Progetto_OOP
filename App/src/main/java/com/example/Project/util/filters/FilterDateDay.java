package com.example.Project.util.filters;

import java.time.LocalDate;

import com.example.Project.model.Event;
import com.example.Project.util.other.Converter;

public class FilterDateDay implements Filter {
	
	public boolean okFilter (Event e,String param) {
		LocalDate newDate = Converter.dateConv(e.getDate());
		LocalDate paramDate = Converter.dateConv(param);
		if(newDate.equals(paramDate))
			return true;
		else return false;
	}

}
