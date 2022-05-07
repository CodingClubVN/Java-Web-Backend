package com.se.codingclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.service.CategorySevice;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategorySevice categorySevice;
	
	@GetMapping("/list")
	public Object getCategoies() {
		return categorySevice.getCategories();
	}
}
