package com.command.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.command.UnitTestUtil;

import com.command.controller.*;


import com.command.controller.Controller;
import com.command.model.Cra;
import com.command.services.CraService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=Controller.class,secure = false)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CraService service;

	@Test
	public void findallCra() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start1 = sdf.parse("2019-12-12");
		Date end1 = sdf.parse("2019-12-20");
		Date start2 = sdf.parse("2019-11-13");
		Date end2 = sdf.parse("2019-11-20");

		Cra firstCra = new Cra(30, (java.sql.Date) start1, (java.sql.Date) end1, 12, 8, null, null, null);
		   Cra secondCra = new Cra(31, (java.sql.Date) start2, (java.sql.Date) end2, 11, 7, null, null, null);

		   when(service.getAll()).thenReturn(Arrays.asList(firstCra, secondCra));
		   
	        mockMvc.perform(get("/cras")  .contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
	                )
	                .andExpect(status().isOk())
	                
	                .andExpect(jsonPath("$",Matchers.hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is(30)))
	               .andExpect(jsonPath("$[0].start_date", is(start1)))
	               .andExpect(jsonPath("$[0].end_date", is(end1)))
	                .andExpect(jsonPath("$[0].month", is(6)))
	                .andExpect(jsonPath("$[0].working_days_count", is(7)))
	                .andExpect(jsonPath("$[1].id", is(31)))
                  .andExpect(jsonPath("$[1].start_date", is(start2)))
                    .andExpect(jsonPath("$[1].end_date", is(end2)))
                    .andExpect(jsonPath("$[1].month", is(7)))
                    .andExpect(jsonPath("$[1].working_days_count", is(10)));          
	              
	        verify(service, times(1)).getAll();
	        verifyNoMoreInteractions(service);
		
	
	}
	
	
	
		
		
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	
	

}




