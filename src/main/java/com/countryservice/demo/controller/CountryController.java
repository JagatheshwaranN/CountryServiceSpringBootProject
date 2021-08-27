package com.countryservice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.bean.CountryResponse;
import com.countryservice.demo.service.CountryService;

/**
 * 
 * @author Jaga
 *
 */

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	private Country country, updatedCountry;
	private CountryResponse countryResponse;


	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries() {
		try {
			List<Country> countries = countryService.getCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountry/{ctryId}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "ctryId") int ctryId) {
		try {
			country = countryService.getCountryById(ctryId);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountry/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String ctryName) {
		try {
			country = countryService.getCountryByName(ctryName);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country ctry) {
		try {
			country = countryService.addCountry(ctry);
			return new ResponseEntity<Country>(country, HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/updatecountry/{ctryId}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "ctryId") int ctryId,
			@RequestBody Country updateCtry) {
		try {
			country = countryService.getCountryById(ctryId);
			country.setCountryName(updateCtry.getCountryName());
			country.setCountryCapital(updateCtry.getCountryCapital());
			country.setCountryPopulation(updateCtry.getCountryPopulation());
			country.setCountryStates(updateCtry.getCountryStates());
			country.setCountryLanguage(updateCtry.getCountryLanguage());
			country.setCountrySport(updateCtry.getCountrySport());
			updatedCountry = countryService.updateCountry(country);
			return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/deletecountry/{ctryId}")
	public ResponseEntity<CountryResponse> deleteCountry(@PathVariable(value = "ctryId") int ctryId) {
		try {
			countryResponse = countryService.deleteCountry(ctryId);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CountryResponse>(countryResponse, HttpStatus.OK);
	}

	/**
	 * This method is used only for unit testing of delete functionality
	 */
	@DeleteMapping("/deletecountries/{ctryId}")
	public ResponseEntity<Country> deleteCountry1(@PathVariable(value = "ctryId") int ctryId) {
		try {
			countryService.deleteCountry1(ctryId);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	

	

}
