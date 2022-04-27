package com.se.codingclub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.ProductDAO;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;
import com.se.codingclub.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Map<Product, List<Image>> getListProduct() {

		return productDAO.getListProduct();
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return productDAO.getProductById(id);
	}

	@Override
	public Product saveProdcut(Product product) {
		// TODO Auto-generated method stub
		return productDAO.saveProdcut(product);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		productDAO.deleteProduct(id);
	}

	@Override
	public Product updateProdcut(int id, Product product) {
		return productDAO.updateProdcut(id, product);
	}

}
