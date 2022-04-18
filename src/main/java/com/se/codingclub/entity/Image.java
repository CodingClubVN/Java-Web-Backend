package com.se.codingclub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image {

	@Id
	private int id;
	@Column(name = "cloudiary_id")
	private String cloudiaryId;

	@Column(name = "url")
	private String url;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "type")
	private String type;

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCloudiaryId() {
		return cloudiaryId;
	}

	public void setCloudiaryId(String cloudiaryId) {
		this.cloudiaryId = cloudiaryId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
