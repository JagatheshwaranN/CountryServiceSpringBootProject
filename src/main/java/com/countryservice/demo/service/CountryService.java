package com.countryservice.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.bean.CountryResponse;
import com.countryservice.demo.repository.CountryRepository;

/**
 * 
 * @author Jaga
 *
 */
@Component
@Service
public class CountryService {

	private Country country;

	@Autowired
	CountryRepository countryRepo;

	@Autowired
	CountryResponse countryResponse;

	public List<Country> getCountries() {
		return countryRepo.findAll();
	}

	public Country getCountryById(int countryId) {
		// return countryRepo.findById(countryId).get();
		List<Country> countries = countryRepo.findAll();
		for (Country ctry : countries) {
			if (ctry.getCountryId() == countryId)
				country = ctry;
		}
		return country;
	}

	public Country getCountryByName(String countryName) {
		List<Country> countries = countryRepo.findAll();
		for (Country ctry : countries) {
			if (ctry.getCountryName().equalsIgnoreCase(countryName))
				country = ctry;
		}
		return country;
	}

	public Country addCountry(Country country) {
		country.setCountryId(getMaxCountryId());
		countryRepo.save(country);
		return country;
	}

	public Country updateCountry(Country country) {
		countryRepo.save(country);
		return country;
	}

	public CountryResponse deleteCountry(int countryId) {
		countryRepo.deleteById(countryId);
		countryResponse.setMessage("The country is deleted successfully");
		countryResponse.setCountryId(countryId);
		return countryResponse;
	}

	/**
	 * This method is used only for unit testing of delete functionality
	 */
	public void deleteCountry1(int countryId) {
		countryRepo.deleteById(countryId);	
	}

	public int getMaxCountryId() {
		return countryRepo.findAll().size() + 1;
	}

}
