package com.countryservice.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.countryservice.demo.bean.CountryBean;
import com.countryservice.demo.bean.CountryResponse;

/**
 * 
 * @author Jaga
 *
 */

@Component
public class CountryService {

	private static HashMap<Integer, CountryBean> countryMap;
	private CountryBean country;
	private CountryResponse countryResponse;

	public CountryService() {
		countryMap = new HashMap<Integer, CountryBean>();
		CountryBean indiaCountry = new CountryBean(1, "India", "Delhi");
		CountryBean usaCountry = new CountryBean(2, "Usa", "Washington");
		CountryBean ukCountry = new CountryBean(3, "United Kingdom", "London");
		countryMap.put(1, indiaCountry);
		countryMap.put(2, usaCountry);
		countryMap.put(3, ukCountry);
	}

	public List<CountryBean> getCountries() {
		List<CountryBean> countries = new ArrayList<CountryBean>(countryMap.values());
		return countries;
	}

	public CountryBean getCountryById(int countryId) {
		country = countryMap.get(countryId);
		return country;
	}

	public CountryBean getCountryByName(String countryName) {
		country = null;
		for (int id : countryMap.keySet()) {
			if (countryMap.get(id).getCountryName().equals(countryName)) {
				country = countryMap.get(id);
			}
		}
		return country;
	}

	public CountryBean addCountry(CountryBean country) {
		country.setCountryId(getMaxCountryId());
		countryMap.put(country.getCountryId(), country);
		return country;
	}

	public CountryBean updateCountry(CountryBean country) {
		if (country.getCountryId() > 0) {
			countryMap.put(country.getCountryId(), country);
		}
		return country;
	}

	public CountryResponse deleteCountry(int countryId) {
		countryMap.remove(countryId);
		countryResponse = new CountryResponse();
		countryResponse.setMessage("Country deleted successfully..");
		countryResponse.setCountryId(countryId);
		return countryResponse;
	}

	public static int getMaxCountryId() {
		int maxCountryId = 0;
		for (int countryId : countryMap.keySet()) {
			if (maxCountryId <= countryId) {
				maxCountryId = countryId;
			}
		}
		return maxCountryId + 1;
	}
}
