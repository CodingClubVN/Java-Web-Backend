package com.se.codingclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.CategoryDAO;
import com.se.codingclub.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		String sql = "select * from categories";
		Session session = sessionFactory.getCurrentSession();
		list = session.createNativeQuery(sql, Category.class).getResultList();
		return list;
	}

}
