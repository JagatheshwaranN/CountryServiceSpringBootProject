package com.countryservice.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Jaga
 *
 */

@Entity
@Table(name = "Country")
public class Country {

	@Id
	@Column(name = "CTRY_ID")
	private int countryId;

	@Column(name = "CTRY_NAME")
	private String countryName;

	@Column(name = "CTRY_CAPITAL")
	private String countryCapital;

	@Column(name = "CTRY_POPULATION")
	private int countryPopulation;

	@Column(name = "CTRY_STATES")
	private int countryStates;

	@Column(name = "CTRY_LANGUAGE")
	private String countryLanguage;

	@Column(name = "CTRY_SPORT")
	private String countrySport;

	public Country() {

	}

	public Country(int countryId, String countryName, String countryCapital, int countryPopulation, int countryStates,
			String countryLanguage, String countrySport) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
		this.countryPopulation = countryPopulation;
		this.countryStates = countryStates;
		this.countryLanguage = countryLanguage;
		this.countrySport = countrySport;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}

	public int getCountryPopulation() {
		return countryPopulation;
	}

	public void setCountryPopulation(int countryPopulation) {
		this.countryPopulation = countryPopulation;
	}

	public int getCountryStates() {
		return countryStates;
	}

	public void setCountryStates(int countryStates) {
		this.countryStates = countryStates;
	}

	public String getCountryLanguage() {
		return countryLanguage;
	}

	public void setCountryLanguage(String countryLanguage) {
		this.countryLanguage = countryLanguage;
	}

	public String getCountrySport() {
		return countrySport;
	}

	public void setCountrySport(String countrySport) {
		this.countrySport = countrySport;
	}

}
