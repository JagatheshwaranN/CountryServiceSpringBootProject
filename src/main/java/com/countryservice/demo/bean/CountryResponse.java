package com.countryservice.demo.bean;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Jaga
 *
 */

@Component
public class CountryResponse {

	private int countryId;
	private String message;

	public CountryResponse() {
		
	}
	
	public CountryResponse(int countryId, String message) {
		this.countryId = countryId;
		this.message = message;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
