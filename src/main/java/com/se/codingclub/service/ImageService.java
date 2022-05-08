package com.se.codingclub.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.entity.Image;

public interface ImageService {

	public List<Image> getListImageProductById(int productId);

	public List<Image> getListImageBrandById(int brandId);

	public ImageDTO saveImage(String productId, String brandId, String type, MultipartFile file);

	public void deleteImage(int id);

	public Image getImageById(int id);
	public ImageDTO updateImage(int image_id, MultipartFile file);
}
