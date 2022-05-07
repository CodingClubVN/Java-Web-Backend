package com.se.codingclub.service;

import java.util.List;
import java.util.Map;

import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;

public interface ProductService {

	public Map<Product, List<Image>> getListProduct();
	public Map<Product, List<Image>> getProductById(int id);
	public Product saveProdcut(Product product);
	public void deleteProduct(int id);
	public Product updateProdcut(int id, Product product);
	public Product getById(int id);
	public Map<Product, List<Image>> getListProductByBrand(int brand_id);
}
