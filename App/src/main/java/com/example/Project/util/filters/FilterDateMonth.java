package com.example.Project.util.filters;

import java.time.LocalDate;

import com.example.Project.model.Event;
import com.example.Project.util.other.Converter;

public class FilterDateMonth implements Filter{
	
	
	public boolean okFilter (Event e,String param) {
		String newParam = param.concat("-01");
		LocalDate newDate = Converter.dateConv(e.getDate());
		LocalDate paramDate = Converter.dateConv(newParam);
		if(newDate.getMonthValue() == paramDate.getMonthValue() && newDate.getYear() == paramDate.getYear())
			return true;
		else return false;
	}

}
