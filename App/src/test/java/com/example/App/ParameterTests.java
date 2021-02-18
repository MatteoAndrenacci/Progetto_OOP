package com.example.App;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Project.exception.InvalidFormatException;
import com.example.Project.exception.InvalidParameterException;
import com.example.Project.init.InitData;
import com.example.Project.model.Event;
import com.example.Project.service.ApplyFilter;

/**
 * Classe di test per lancio eccezioni di tipo InvalidParameterException
 * @author matteoandrenacci
 * @author eleonorabrasili
 */

public class ParameterTests {
	
	private ArrayList<Event> eventsList = null;
	
	@BeforeEach
	void setUp() throws Exception {
		eventsList = new ArrayList<Event>(InitData.getDatafromJson());
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Quando il filtro è vuoto
	 */
	@Test
	void test() {
		String param = "{" + "}";
		
		try {
			JSONObject filter = (JSONObject) JSONValue.parseWithException(param);
			assertThrows(InvalidParameterException.class,  ()-> ApplyFilter.checkFilter(filter,eventsList));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Quando il parametro del filtro è vuoto
	 */
	
	@Test
	void test1() {
		String param = "{\r\n" + "\"DateDay\":\r\n" + "}";
		
		try {
			JSONObject filter = (JSONObject) JSONValue.parseWithException(param);
			assertThrows(InvalidParameterException.class,  ()-> ApplyFilter.checkFilter(filter,eventsList));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test2() {
		String param = "{\r\n" + "\"DateDay\": [] \r\n" + "}";
		
		try {
			JSONObject filter = (JSONObject) JSONValue.parseWithException(param);
			assertThrows(InvalidParameterException.class,  ()-> ApplyFilter.checkFilter(filter,eventsList));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
