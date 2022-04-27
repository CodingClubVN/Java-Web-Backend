package com.se.codingclub.dao;

import java.util.List;
import java.util.Map;

import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;

public interface ProductDAO {
	
	public Map<Product, List<Image>> getListProduct();
	public Product getProductById(int id);
	public Product saveProdcut(Product product);
	public void deleteProduct(int id);
	public Product updateProdcut(int id, Product product);
}
