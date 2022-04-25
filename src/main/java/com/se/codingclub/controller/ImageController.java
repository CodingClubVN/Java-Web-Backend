package com.se.codingclub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.entity.Image;
import com.se.codingclub.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@GetMapping("/product")
	public List<Image> getImageProduct(@RequestParam int productId) {
		return imageService.getListImageProductById(productId);
	}

	@GetMapping("/product/files/{productId}")
	public ResponseEntity<List<byte[]>> getFileImage(@PathVariable int productId) {
		List<Image> images = imageService.getListImageProductById(productId);
		List<byte[]> files = new ArrayList<byte[]>();
		for (int i = 0; i < images.size(); i++) {
			files.add(images.get(i).getFile());
		}
		return ResponseEntity.ok().body(files);
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getFileImageById(@PathVariable int id) {
		Image image = imageService.getImageById(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileType() + "\"")
				.body(image.getFile());
	}

	@PostMapping("/new")
	public ImageDTO saveImage(@RequestParam("file") MultipartFile file, @RequestParam("productId") String productId,
			@RequestParam("brandId") String brandId, @RequestParam("type") String type) throws IOException {

		return imageService.saveImage(productId, brandId, type, file);
	}
}
