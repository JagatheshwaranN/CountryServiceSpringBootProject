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

import com.countryservice.demo.bean.CountryBean;
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
	public List<CountryBean> getCountries() {
		return countryService.getCountries();
	}

	@GetMapping("/getcountries/{ctryId}")
	public CountryBean getCountryById(@PathVariable(value = "ctryId") int ctryId) {
		return countryService.getCountryById(ctryId);
	}

	@GetMapping("/getcountries/countryname")
	public CountryBean getCountryByName(@RequestParam(value = "name") String ctryName) {
		return countryService.getCountryByName(ctryName);
	}

	@PostMapping("/addcountry")
	public CountryBean addCountry(@RequestBody CountryBean country) {
		return countryService.addCountry(country);
	}

	@PutMapping("/updatecountry")
	public CountryBean updateCountry(@RequestBody CountryBean country) {
		return countryService.updateCountry(country);
	}

	@DeleteMapping("/deletecountry/{ctryId}")
	public CountryResponse deleteCountry(@PathVariable(value = "ctryId") int ctryId) {
		return countryService.deleteCountry(ctryId);
	}

}
