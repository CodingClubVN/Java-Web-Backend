package com.se.codingclub.dto;

public class BrandDTO {

	private int id;
	private String name;
	private String country;
	private int founderYear;
	private String description;

	public BrandDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getFounderYear() {
		return founderYear;
	}

	public void setFounderYear(int founderYear) {
		this.founderYear = founderYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
