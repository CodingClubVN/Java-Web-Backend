package com.se.codingclub.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CartDetailPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int product;
	private int shoppingSession;

	@Override
	public int hashCode() {
		return Objects.hash(product, shoppingSession);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDetailPK other = (CartDetailPK) obj;
		return product == other.product && shoppingSession == other.shoppingSession;
	}

}
