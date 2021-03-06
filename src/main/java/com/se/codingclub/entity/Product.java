package com.se.codingclub.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "At least 4 chars/digits and can't more than 30 chars/digits")
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "price")
	private double price;
	@NotNull
	@Column(name = "fuel_type")
	private String fuelType;
	@NotNull
	@Column(name = "body_type")
	private String bodyType;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "update_date")
	private Date updatedDate;
	@NotNull
	@Column(name = "status")
	private String status;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;

	@OneToMany(mappedBy = "product")
	private List<Image> images;
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product")
	private List<CartDetail> cartDetails;

	public Product() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
