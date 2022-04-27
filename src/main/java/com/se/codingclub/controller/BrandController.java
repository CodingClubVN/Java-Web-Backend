package com.se.codingclub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.se.codingclub.dto.BrandDTO;
import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;
import com.se.codingclub.service.BrandService;
@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/list")
	public ResponseEntity<List<BrandDTO>> getBrands() {
		Map<Brand, List<Image>> map = brandService.getListBrand();
		List<BrandDTO> brandDTOs = new ArrayList<BrandDTO>();
		map.entrySet().forEach(entry -> {
			Brand brandTemp = entry.getKey();
			List<Image> images = entry.getValue();
			BrandDTO brandDTO = new BrandDTO();

			brandDTO.setName(brandTemp.getName());
			brandDTO.setId(brandTemp.getId());
			brandDTO.setFounderYear(brandTemp.getFounderYear());
			brandDTO.setDescription(brandTemp.getDescription());
			brandDTO.setCountry(brandTemp.getCountry());

			List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
			for (Image image : images) {
				ImageDTO imageDTO = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
						.path(image.getId() + "").toUriString();
				imageDTO.setId(image.getId());
				imageDTO.setUrl(url);
				imageDTO.setType(image.getType());
				imageDTOs.add(imageDTO);
			}
			brandDTO.setImageDTOs(imageDTOs);
			brandDTOs.add(brandDTO);
		});
		return ResponseEntity.ok().body(brandDTOs);
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
