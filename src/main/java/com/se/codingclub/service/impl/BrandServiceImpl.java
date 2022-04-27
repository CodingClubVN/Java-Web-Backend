package com.se.codingclub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.codingclub.dao.BrandDAO;
import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;
import com.se.codingclub.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDAO brandDAO;

	@Override
	public Map<Brand, List<Image>> getListBrand() {
		// TODO Auto-generated method stub
		return brandDAO.getListBrand();
	}

	@Override
	public Brand getBrandById(int id) {

		return brandDAO.getBrandById(id);
	}

	@Override
	public void deleteBrand(int id) {
		brandDAO.deleteBrand(id);

	}

	@Override
	public Brand saveBrand(Brand brand) {
		return brandDAO.saveBrand(brand);
	}

	@Override
	public Brand updateBrand(int id, Brand brand) {

		return brandDAO.updateBrand(id, brand);
	}

}
