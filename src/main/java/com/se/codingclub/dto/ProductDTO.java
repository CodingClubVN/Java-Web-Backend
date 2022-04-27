package com.se.codingclub.dto;

import java.util.Date;
import java.util.List;

public class ProductDTO {

	private int id;
	private String name;
	private double price;
	private String fuelType;
	private String bodyType;
	private Date createdDate;
	private Date updatedDate;
	private List<ImageDTO> imageDTOs;

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(int id, String name, double price, String fuelType, String bodyType, Date createdDate,
			Date updatedDate, List<ImageDTO> imageDTOs) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.fuelType = fuelType;
		this.bodyType = bodyType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.imageDTOs = imageDTOs;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<ImageDTO> getImageDTOs() {
		return imageDTOs;
	}

	public void setImageDTOs(List<ImageDTO> imageDTOs) {
		this.imageDTOs = imageDTOs;
	}

	

}
