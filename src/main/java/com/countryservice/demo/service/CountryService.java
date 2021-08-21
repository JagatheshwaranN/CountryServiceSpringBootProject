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
	private CountryResponse countryResponse;

	@Autowired
	CountryRepository countryRepo;

	public List<Country> getCountries() {
		return countryRepo.findAll();
	}

	public Country getCountryById(int countryId) {
		return countryRepo.findById(countryId).get();
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

	public int getMaxCountryId() {
		return countryRepo.findAll().size() + 1;
	}

}
