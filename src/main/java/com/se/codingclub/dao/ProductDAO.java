package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.Product;

public interface ProductDAO {
	
	public List<Product> getListProduct();
	public Product getProductById(int id);
	public Product saveProdcut(Product product);
	public void deleteProduct(int id);
	public Product updateProdcut(int id, Product product);
}
