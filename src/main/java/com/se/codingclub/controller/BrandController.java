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
import com.se.codingclub.entity.Brand;
import com.se.codingclub.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/list")
	public List<Brand> getBrands() {
		return brandService.getListBrand();

	}

	@GetMapping("/{id}")
	public Brand getBrand(@PathVariable String id) {
		return brandService.getBrandById(Integer.parseInt(id));
	}

	@PostMapping("/new")
	public Brand saveBrand(@RequestBody Brand brand) {

		return brandService.saveBrand(brand);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteBrand(@PathVariable String id) {
		try {
			brandService.deleteBrand(Integer.parseInt(id));
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete"));
		}
	}

	@PutMapping("/{id}")
	public Brand updateBrand(@PathVariable String id, @RequestBody Brand brand) {

		return brandService.updateBrand(Integer.parseInt(id), brand);

	}
}
