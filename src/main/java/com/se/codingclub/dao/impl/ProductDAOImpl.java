package com.se.codingclub.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.ProductDAO;
import com.se.codingclub.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Product> getListProduct() {

		String query = "Select * from products";
		Session session = sessionFactory.getCurrentSession();
		List<Product> products = session.createNativeQuery(query, Product.class).getResultList();
		return products;
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.find(Product.class, id);

		return product;
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
		if(product.getFuelType() != null)
		productOld.setFuelType(product.getFuelType());
		if(product.getName() != null)
		productOld.setName(product.getName());
		if(product.getPrice() != 0)
		productOld.setPrice(product.getPrice());
		productOld.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		session.merge(productOld);
		return productOld;
	}

}
