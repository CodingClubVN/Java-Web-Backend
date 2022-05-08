package com.se.codingclub.dao;

import java.util.List;

import com.se.codingclub.entity.Image;

public interface ImageDAO {

	public List<Image> getListImageProductById(int productId);

	public List<Image> getListImageBrandById(int brandId);

	public Image saveImage(Image image);

	public void deleteImage(int id);

	public Image getImageById(int id);
	
	public Image updateImage(int image_id,Image image);

}
