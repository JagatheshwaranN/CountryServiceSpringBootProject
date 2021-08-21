package com.countryservice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/getcountries")
	public List<Country> getCountries() {

	}

	@GetMapping("/getcountries/{ctryId}")
	public Country getCountryById(@PathVariable(value = "ctryId") int ctryId) {

	}

	@GetMapping("/getcountries/countryname")
	public Country getCountryByName(@RequestParam(value = "name") String ctryName) {

	}

	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {

	}

	@PutMapping("/updatecountry")
	public Country updateCountry(@RequestBody Country country) {

	}

	@DeleteMapping("/deletecountry/{ctryId}")
	public CountryResponse deleteCountry(@PathVariable(value = "ctryId") int ctryId) {
		
	}

}
