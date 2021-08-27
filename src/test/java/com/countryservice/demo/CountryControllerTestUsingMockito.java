package com.countryservice.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.controller.CountryController;
import com.countryservice.demo.service.CountryService;

/**
 * 
 * @author Jaga
 *
 */

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { CountryControllerTestUsingMockito.class })
public class CountryControllerTestUsingMockito {

	@Mock
	CountryService countryService;

	@InjectMocks
	CountryController countryController;

	
	private ResponseEntity<List<Country>> listResponse;
	private ResponseEntity<Country> response;
	private List<Country> mockCountryData;
	private Country country;
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
	public void test_getCountries() {
		when(countryService.getCountries()).thenReturn(mockCountries());
		listResponse = countryController.getCountries();
		Assertions.assertEquals(HttpStatus.FOUND, listResponse.getStatusCode());
		Assertions.assertEquals(2, listResponse.getBody().size());
	}

	@Test
	@Order(2)
	public void test_getCountryById() {
		countryId = 1;
		when(countryService.getCountryById(countryId)).thenReturn(mockCountry());
		response = countryController.getCountryById(countryId);
		Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
		Assertions.assertEquals(countryId, response.getBody().getCountryId());
	}

	@Test
	@Order(3)
	public void test_getCountryByName() {
		String countryName = "India";
		when(countryService.getCountryByName(countryName)).thenReturn(mockCountry());
		response = countryController.getCountryByName(countryName);
		Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
		Assertions.assertEquals(countryName, response.getBody().getCountryName());
	}

	@Test
	@Order(4)
	public void test_addCountry() {
		country = new Country(3, "Uk", "London", 30000, 20, "English", "Cricket");
		when(countryService.addCountry(country)).thenReturn(country);
		response = countryController.addCountry(country);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals(country, response.getBody());
	}

	@Test
	@Order(5)
	public void test_updateCountry() {
		countryId = 3;
		country = new Country(3, "Uk", "London", 20000, 25, "English", "Cricket");
		when(countryService.getCountryById(countryId)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		response = countryController.updateCountry(countryId, country);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(countryId, response.getBody().getCountryId());
		Assertions.assertEquals(20000, response.getBody().getCountryPopulation());
		Assertions.assertEquals(25, response.getBody().getCountryStates());
	}

	@Test
	@Order(6)
	public void test_deleteCountry() {
		countryId = 3;
		doNothing().when(countryService).deleteCountry1(countryId);
		response = countryController.deleteCountry1(countryId);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
