package com.example.App;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

public class FormatTests {
	
	private ArrayList<Event> eventsList = null;
	
	@BeforeEach
	void setUp() throws Exception {
		eventsList = new ArrayList<Event>(InitData.getDatafromJson());
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test() {
		String param = "{\r\n" + "\"Test\":[\"x\"]\r\n" + "}";
		
		try {
			JSONObject filter = (JSONObject) JSONValue.parseWithException(param);
			assertThrows(InvalidFormatException.class,  ()-> ApplyFilter.checkFilter(filter,eventsList));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test1() {
		String param = "{\r\n" + "\"DateDay\": \"x\" \r\n" + "}";
		
		try {
			JSONObject filter = (JSONObject) JSONValue.parseWithException(param);
			assertThrows(InvalidFormatException.class,  ()-> ApplyFilter.checkFilter(filter,eventsList));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
