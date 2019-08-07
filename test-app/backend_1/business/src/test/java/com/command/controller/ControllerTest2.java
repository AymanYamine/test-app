package com.command.controller;



import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.command.model.Cra;
import com.command.model.Document;

public class ControllerTest2 extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
		
	}
public Date getDate(int day , int month , int year) {
	Calendar cal = Calendar.getInstance();
        cal.set( cal.YEAR, year );
    cal.set( cal.MONTH, month );
    cal.set( cal.DATE, day );
    
    cal.set( cal.HOUR_OF_DAY, 0 );
    cal.set( cal.MINUTE, 0 );
    cal.set( cal.SECOND, 0 );
    cal.set( cal.MILLISECOND, 0 );
    
    java.sql.Date jsqlD = 
       new java.sql.Date( cal.getTime().getTime() );
    return jsqlD ;
	
}
	@Test
	public void getCraTest() throws Exception {
		String uri = "/cras";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Cra[] cralist = super.mapFromJson(content, Cra[].class);
		assertTrue(cralist.length > 0);
		System.out.println("not tested");
	}

	@Test
	public void createCra() throws Exception {
		String uri = "/cras";
		Cra cra = new Cra();
		cra.setId(5);
		cra.setMonth(7);
		cra.setWorking_days_count(20);
		cra.setStart_date(getDate(7, 7, 2019));
		cra.setEnd_date(getDate(30, 7, 2019));
		cra.setWorking_days_count(9);
		cra.setDocument(new Document(20, "name", "location", "pdf", null, null));
		String inputJson = super.mapToJson(cra);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
			   
			   int status = mvcResult.getResponse().getStatus();
			   assertEquals(201, status);
		
				
	}

	@Test
	public void updateCra() throws Exception {
		 String uri = "/cras/update/5";
		   Cra cra = new Cra();
		   cra.setId(6);
			cra.setMonth(8);
			cra.setWorking_days_count(10);
			cra.setStart_date(getDate(20, 8, 2019));
			cra.setEnd_date(getDate(30, 8, 2019));
			cra.setDocument(new Document(20, "name", "location", "pdf", null, null));

		   String inputJson = super.mapToJson(cra);
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		

	}

	

	@Test
	public void deleteCraTest() throws Exception {
		
		
		String uri = "/cras/364";
		System.out.println(uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

}
