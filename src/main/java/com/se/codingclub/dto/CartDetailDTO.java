package com.se.codingclub.dto;

import java.util.Date;

public class CartDetailDTO {

	private int id;
	private ProductDTO productDTO;
	private ShoppingSessionDTO shoppingSessionDTO;
	private int quantity;
	private Date createdDate;
	private Date modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public ShoppingSessionDTO getShoppingSessionDTO() {
		return shoppingSessionDTO;
	}

	public void setShoppingSessionDTO(ShoppingSessionDTO shoppingSessionDTO) {
		this.shoppingSessionDTO = shoppingSessionDTO;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
