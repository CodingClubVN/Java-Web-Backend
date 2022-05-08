package com.se.codingclub.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.codingclub.dao.ImageDAO;
import com.se.codingclub.entity.Image;

@Repository
public class ImageDAOImpl implements ImageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Image> getListImageProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		List<Image> imageProduct = new ArrayList<Image>();
		String query = "Select i.id, i.product_id, i.type, i.file, i.brand_id  from  images i join products p on i.product_id = p.id where i.product_id = "
				+ productId;
		imageProduct = session.createNativeQuery(query, Image.class).getResultList();

		return imageProduct;
	}

	@Override
	@Transactional
	public List<Image> getListImageBrandById(int brandId) {
		Session session = sessionFactory.getCurrentSession();
		List<Image> imageBrand = new ArrayList<Image>();
		String query = "select * from images where brand_id = " + brandId;
		imageBrand = session.createNativeQuery(query, Image.class).getResultList();
		return imageBrand;
	}

	@Override
	@Transactional
	public Image saveImage(Image image) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(image);
		return image;
	}

	@Override
	@Transactional
	public void deleteImage(int id) {
		Session session = sessionFactory.getCurrentSession();
		Image image = session.find(Image.class, id);
		session.delete(image);
	}
	@Override
	@Transactional
	public Image getImageById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Image image = session.find(Image.class, id);
		return image;
	}

	@Override
	@Transactional
	public Image updateImage(int image_id,Image image) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Image imageOld = session.find(Image.class, image_id);
		imageOld.setFile(image.getFile());
		imageOld.setFileType(image.getFileType());
		session.merge(imageOld);
		return imageOld;
	}

}
