package com.countryservice.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.controller.CountryController;
import com.countryservice.demo.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Jaga
 *
 */
@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.countryservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { CountryControllerTestUsingMockMVC.class })
public class CountryControllerTestUsingMockMVC {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CountryService countryService;

	@InjectMocks
	CountryController countryController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}

	private List<Country> mockCountryData;
	private Country country;
	private ObjectMapper mapper;
	private int countryId;

	public List<Country> mockCountries() {
		mockCountryData = new ArrayList<Country>();
		mockCountryData.add(new Country(1, "India", "Delhi", 100000, 25, "Tamil", "Hockey"));
		mockCountryData.add(new Country(2, "Usa", "Washington", 50000, 40, "English", "Baseball"));
		return mockCountryData;
	}

	public Country mockCountry() {
		country = new Country(1, "India", "Delhi", 100000, 25, "Tamil", "Hockey");
		return country;
	}

	@Test
	@Order(1)
	public void test_getCountries() throws Exception {
		when(countryService.getCountries()).thenReturn(mockCountries());
		this.mockMvc.perform(get("/getcountries"))
		.andExpect(status().isFound())
		.andDo(print());

	}

	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
		countryId = 1;
		when(countryService.getCountryById(countryId)).thenReturn(mockCountry());
		this.mockMvc.perform(get("/getcountry/{ctryId}", countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(countryId))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
		.andDo(print());
	}

	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		String countryName = "India";
		when(countryService.getCountryByName(countryName)).thenReturn(mockCountry());
		this.mockMvc.perform(get("/getcountry/countryname").param("name", countryName))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
		.andDo(print());
	}

	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
		country = new Country(3, "Uk", "London", 30000, 20, "English", "Cricket");
		when(countryService.addCountry(country)).thenReturn(country);
		mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(country);
		this.mockMvc.perform(post("/addcountry")
		.content(jsonBody)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andDo(print());
	}

	@Test
	@Order(5)
	public void test_updateCountry() throws Exception {
		countryId = 3;
		country = new Country(3, "Uk", "London", 20000, 25, "English", "Cricket");
		when(countryService.getCountryById(countryId)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(country);
		this.mockMvc.perform(put("/updatecountry/{ctryId}", countryId)
		.content(jsonBody)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryId").value(countryId))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Uk"))
		.andDo(print());
	}

	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception {
		countryId = 3;
		when(countryService.getCountryById(countryId)).thenReturn(mockCountry());
		this.mockMvc.perform(delete("/deletecountry/{ctryId}", countryId))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
