package com.se.codingclub.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.se.codingclub.dao.BrandDAO;
import com.se.codingclub.dao.ImageDAO;
import com.se.codingclub.dao.ProductDAO;
import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.entity.Image;
import com.se.codingclub.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDAO imageDAO;
	@Autowired
	private BrandDAO brandDAO;
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Image> getListImageProductById(int productId) {
		// TODO Auto-generated method stub
		return imageDAO.getListImageProductById(productId);
	}

	@Override
	public List<Image> getListImageBrandById(int brandId) {
		return imageDAO.getListImageBrandById(brandId);
	}

	@Override
	public void deleteImage(int id) {
		imageDAO.deleteImage(id);

	}

	@Override
	public Image getImageById(int id) {
		// TODO Auto-generated method stub
		return imageDAO.getImageById(id);
	}

	@Override
	public ImageDTO saveImage(String productId, String brandId, String type, MultipartFile file) {
		Image image = new Image();
		try {

			if (!brandId.equals(""))
				image.setBrand(brandDAO.getBrandById(Integer.parseInt(brandId)));
			else
				image.setBrand(null);
			if (!productId.equals(""))
				image.setProduct(productDAO.getById(Integer.parseInt(productId)));
			else
				image.setProduct(null);
			image.setFile(file.getBytes());
			String typeFile = StringUtils.cleanPath(file.getOriginalFilename());
			image.setFileType(typeFile);
			image.setType(type);
			System.out.println(brandId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image = imageDAO.saveImage(image);

		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setType(image.getType());
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(image.getId() + "")
				.toUriString();
		imageDTO.setUrl(url);
		return imageDTO;
	}

	@Override
	public ImageDTO updateImage(int image_id, MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			System.out.println("aaaaa");
			Image image = new Image();
			image.setFile(file.getBytes());
			String typeFile = StringUtils.cleanPath(file.getOriginalFilename());
			image.setFileType(typeFile);
			image.setId(image_id);
			image = imageDAO.updateImage(image_id, image);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setId(image.getId());
			imageDTO.setType(image.getType());
			String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(image.getId() + "")
					.toUriString();
			imageDTO.setUrl(url);
			return imageDTO;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
