package com.countryservice.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.repository.CountryRepository;
import com.countryservice.demo.service.CountryService;

/**
 * 
 * @author Jaga
 *
 */

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { CountryServiceTestUsingMockito.class })
public class CountryServiceTestUsingMockito {

	@Mock
	CountryRepository countryRepo;

	@InjectMocks
	CountryService countryService;

	private List<Country> mockCountryData;
	private Country country;

	public List<Country> mockCountries() {
		mockCountryData = new ArrayList<Country>();
		mockCountryData.add(new Country(1, "India", "Delhi", 100000, 25, "Tamil", "Hockey"));
		mockCountryData.add(new Country(2, "Usa", "Washington", 50000, 40, "English", "Baseball"));
		return mockCountryData;
	}

	public Country mockCountry() {
		country = new Country(1, "India", "Delhi", 100000, 25, "Hindi", "Hockey");
		return country;
	}

	@Test
	@Order(1)
	public void test_getCountries() {
		when(countryRepo.findAll()).thenReturn(mockCountries());
		Assertions.assertEquals(2, countryService.getCountries().size());
	}

	@Test
	@Order(2)
	public void test_getCountryById() {
		int countryId = 1;
		when(countryRepo.findAll()).thenReturn(mockCountries());
		Assertions.assertEquals(1, countryService.getCountryById(countryId).getCountryId());
	}

	@Test
	@Order(3)
	public void test_getCountryByName() {
		String countryName = "India";
		when(countryRepo.findAll()).thenReturn(mockCountries());
		Assertions.assertEquals(countryName, countryService.getCountryByName(countryName).getCountryName());
	}

	@Test
	@Order(4)
	public void test_addCountry() {
		country = new Country(3, "Uk", "London", 30000, 20, "English", "Cricket");
		when(countryRepo.save(country)).thenReturn(country);
		Assertions.assertEquals(country, countryService.addCountry(country));
	}

	@Test
	@Order(5)
	public void test_updateCountry() {
		country = new Country(3, "Uk", "London", 25000, 25, "English", "Cricket");
		when(countryRepo.save(country)).thenReturn(country);
		Assertions.assertEquals(country, countryService.updateCountry(country));
	}

	@Test
	@Order(6)
	public void test_deleteCountry() {
		countryService.deleteCountry1(mockCountry());
		verify(countryRepo, times(1)).delete(country);
	}

}
