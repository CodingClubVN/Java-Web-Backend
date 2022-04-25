package com.se.codingclub.dao;

import java.util.List;
import java.util.Map;

import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;

public interface BrandDAO {

	public Map<Brand, List<Image>> getListBrand();

	public Brand getBrandById(int id);

	public void deleteBrand(int id);

	public Brand saveBrand(Brand brand);

	public Brand updateBrand(int id, Brand brand);
}
