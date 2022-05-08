package com.se.codingclub.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.ProductDAO;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Map<Product, List<Image>> getListProduct() {
		Map<Product, List<Image>> result = new HashMap<>();
		String query = "Select * from products where status='enable' ORDER BY id desc";
		String queryImage = "Select * from images where product_id = ";
		Session session = sessionFactory.getCurrentSession();
		List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
		for (Product product : products) {
			List<Image> listImage = session.createNativeQuery(queryImage + product.getId(), Image.class)
					.getResultList();
			result.put(product, listImage);
		}
		return result;
	}

	@Override
	@Transactional
	public Map<Product, List<Image>> getProductById(int id) {
		Map<Product, List<Image>> result = new HashMap<>();
		Session session = sessionFactory.getCurrentSession();
		String queryImage = "Select * from images where product_id = ";
		Product product = session.find(Product.class, id);
		List<Image> listImage = session.createNativeQuery(queryImage + product.getId(), Image.class).getResultList();
		result.put(product, listImage);
		return result;
	}

	@Override
	@Transactional
	public Product saveProdcut(Product product) {
		Session session = sessionFactory.getCurrentSession();
		product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		session.persist(product);
		return product;
	}

	@Override
	@Transactional
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Product product = session.find(Product.class, id);
		session.remove(product);
	}

	@Override
	@Transactional
	public Product updateProdcut(int id, Product product) {
		Session session = sessionFactory.getCurrentSession();
		Product productOld = session.find(Product.class, id);
		if (product.getBodyType() != null)
			productOld.setBodyType(product.getBodyType());
		if (product.getBrand() != null)
			productOld.setBrand(product.getBrand());
		if (product.getCategory() != null)
			productOld.setCategory(product.getCategory());
		if (product.getDiscount() != null)
			productOld.setDiscount(product.getDiscount());
		if (product.getFuelType() != null)
			productOld.setFuelType(product.getFuelType());
		if (product.getName() != null)
			productOld.setName(product.getName());
		if (product.getPrice() != 0)
			productOld.setPrice(product.getPrice());
		if (product.getStatus() != null) {
			productOld.setStatus(product.getStatus());
		}
		productOld.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		session.merge(productOld);
		return productOld;
	}

	@Override
	@Transactional
	public Product getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Product.class, id);
	}

	@Override
	@Transactional
	public Map<Product, List<Image>> getListProductByBrand(int brand_id) {
		// TODO Auto-generated method stub
		Map<Product, List<Image>> result = new HashMap<>();
		String query = "Select * from products where brand_id = "+ brand_id+" and status='enable' ORDER BY id desc";
		String queryImage = "Select * from images where product_id = ";
		Session session = sessionFactory.getCurrentSession();
		List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
		for (Product product : products) {
			List<Image> listImage = session.createNativeQuery(queryImage + product.getId(), Image.class)
					.getResultList();
			result.put(product, listImage);
		}
		return result;
	}

	@Override
	@Transactional
	public Map<Product, List<Image>> getListProductByCategory(int category_id) {
		// TODO Auto-generated method stub
		Map<Product, List<Image>> result = new HashMap<>();
		String query = "Select * from products where category_id = "+ category_id + " and status='enable' ORDER BY id desc";
		String queryImage = "Select * from images where product_id = ";
		Session session = sessionFactory.getCurrentSession();
		List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
		for (Product product : products) {
			List<Image> listImage = session.createNativeQuery(queryImage + product.getId(), Image.class)
					.getResultList();
			result.put(product, listImage);
		}
		return result;
	}

}
