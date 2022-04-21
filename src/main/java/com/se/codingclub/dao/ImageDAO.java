package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.Image;

public interface ImageDAO {
	
	public List<Image> getListImageProductById(int productId);
	public List<Image> getListImageBrandById(int brandId);
	public Image saveImage(Image image);
	public void deleteImage(int id);
	
}	
