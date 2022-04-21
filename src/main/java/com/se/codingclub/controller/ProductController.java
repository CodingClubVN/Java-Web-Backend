package com.se.codingclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Product;
import com.se.codingclub.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public List<Product> getProducts() {
		List<Product> products = productService.getListProduct();
		return products;
	}

	@PostMapping("/new")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProdcut(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteProduct(@PathVariable String id) {
		try {

			productService.deleteProduct(Integer.parseInt(id));
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete"));
		}
	}

	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable String id) {
		return productService.updateProdcut(Integer.parseInt(id), product);
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable String id) {
		return productService.getProductById(Integer.parseInt(id));
		
	}
}
