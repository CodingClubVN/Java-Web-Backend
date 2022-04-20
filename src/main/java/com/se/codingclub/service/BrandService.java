package com.se.codingclub.service;

import java.util.List;

import com.se.codingclub.entity.Brand;

public interface BrandService {

	public List<Brand> getListBrand();

	public Brand getBrandById(int id);

	public void deleteBrand(int id);

	public Brand saveBrand(Brand brand);
	
	public Brand updateBrand(int id, Brand brand);
}
