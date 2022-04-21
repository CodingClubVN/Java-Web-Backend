package com.se.codingclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.BrandDAO;
import com.se.codingclub.entity.Brand;

@Repository
public class BrandDAOImpl implements BrandDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Brand> getListBrand() {
		List<Brand> listBrands = new ArrayList<Brand>();
		Session session = sessionFactory.getCurrentSession();
		String query = "Select * from brands";
		listBrands = session.createNativeQuery(query, Brand.class).getResultList();
		return listBrands;
	}

	@Override
	@Transactional
	public Brand getBrandById(int id) {
		Brand brand = new Brand();
		Session session = sessionFactory.getCurrentSession();
		brand = session.find(Brand.class, id);
		return brand;
	}

	@Override
	@Transactional
	public void deleteBrand(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Brand brand = session.find(Brand.class, id);
		session.delete(brand);
	}

	@Override
	@Transactional
	public Brand saveBrand(Brand brand) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(brand);
		return brand;
	}

	@Override
	@Transactional
	public Brand updateBrand(int id, Brand brand) {
		Session session = sessionFactory.getCurrentSession();
		Brand brandOld = session.find(Brand.class, id);
		if (brand.getName() != null)
			brandOld.setName(brand.getName());
		if (brand.getFounderYear() != 0)
			brandOld.setFounderYear(brand.getFounderYear());
		if (brand.getDescription() != null)
			brandOld.setDescription(brand.getDescription());
		if (brand.getCountry() != null)
			brandOld.setCountry(brand.getCountry());
		session.merge(brandOld);
		return brandOld;
	}

}
