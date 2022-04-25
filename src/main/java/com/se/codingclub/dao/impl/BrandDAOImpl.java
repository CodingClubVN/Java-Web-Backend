package com.se.codingclub.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.BrandDAO;
import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;

@Repository
public class BrandDAOImpl implements BrandDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Map<Brand, List<Image>> getListBrand() {
		Map<Brand, List<Image>> result = new HashMap<>();
		List<Brand> listBrands = new ArrayList<Brand>();
		Session session = sessionFactory.getCurrentSession();
		String query = "Select * from brands";
		String queryImage = "Select * from images where brand_id = ";
		listBrands = session.createNativeQuery(query, Brand.class).getResultList();
		for (Brand brand : listBrands) {
			List<Image> images = session.createNativeQuery(queryImage + brand.getId(), Image.class).getResultList();
			result.put(brand, images);

		}
		return result;
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
