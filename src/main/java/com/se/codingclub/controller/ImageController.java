package com.se.codingclub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se.codingclub.dto.Auth;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.ImageService;
import com.se.codingclub.validation.ValidFile;
@CrossOrigin
@RestController
@RequestMapping("/api/images")
@Validated
public class ImageController {

	@Autowired
	private ImageService imageService;
	@Autowired
	private Auth tokenWarp;
	@Autowired
	private AuthService authService;

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

	@GetMapping("/brands/files/{brandId}")
	public ResponseEntity<List<byte[]>> getFileBrandImage(@PathVariable int brandId) {
		List<Image> images = imageService.getListImageProductById(brandId);
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
	public Object saveImage(@RequestParam("file") @Valid @ValidFile MultipartFile file, @RequestParam("productId") String productId,
			@RequestParam("brandId") String brandId, @RequestParam("type") String type) throws IOException {

		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		return imageService.saveImage(productId, brandId, type, file);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteImage(@PathVariable String id) {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		try {
			imageService.deleteImage(Integer.parseInt(id));
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete"));
		}
	}
	@PostMapping("/update/{id}")
	public Object updateImage(@RequestParam("file") @Valid @ValidFile MultipartFile file, @PathVariable String id) throws IOException {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		return imageService.updateImage(Integer.parseInt(id), file);
	}
}
