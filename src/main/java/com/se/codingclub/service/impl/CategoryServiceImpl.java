package com.se.codingclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.CategoryDAO;
import com.se.codingclub.entity.Category;
import com.se.codingclub.service.CategorySevice;

@Service
public class CategoryServiceImpl implements CategorySevice{

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return categoryDAO.getCategories();
	}

	
}
