package com.se.codingclub.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;
	@Column(name = "country")
	private String country;
	@Column(name = "img_url")
	private String imgUrl;
	@Column(name = "img_id_cloud")
	private String imgUrlCloud;
	@Column(name = "founder_year")
	private int founderYear;
	@Column(name = "description")
	private String description;

	
	@OneToMany(mappedBy = "brand")
	private List<Product> products;
	public Brand() {
	}

	public Brand(int id, String name, String country, String imgUrl, String imgUrlCloud, int founderYear,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.imgUrl = imgUrl;
		this.imgUrlCloud = imgUrlCloud;
		this.founderYear = founderYear;
		this.description = description;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUrlCloud() {
		return imgUrlCloud;
	}

	public void setImgUrlCloud(String imgUrlCloud) {
		this.imgUrlCloud = imgUrlCloud;
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
