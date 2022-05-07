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

import com.se.codingclub.dto.Auth;
import com.se.codingclub.dto.BrandDTO;
import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.BrandService;
import com.se.codingclub.service.ImageService;

@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private Auth tokenWarp;
	@Autowired
	private AuthService authService;
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
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api0/images/")
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
	public BrandDTO getBrand(@PathVariable String id) {
		Brand brand = brandService.getBrandById(Integer.parseInt(id));
		List<Image> images = imageService.getListImageBrandById(Integer.parseInt(id));
		List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setName(brand.getName());
		for (Image image : images) {
			ImageDTO imageDTO = new ImageDTO();
			String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/").path(image.getId() + "")
					.toUriString();
			imageDTO.setId(image.getId());
			imageDTO.setUrl(url);
			imageDTO.setType(image.getType());
			imageDTOs.add(imageDTO);
		}
		brandDTO.setImageDTOs(imageDTOs);
		brandDTO.setId(brand.getId());
		brandDTO.setFounderYear(brand.getFounderYear());
		brandDTO.setDescription(brand.getDescription());
		brandDTO.setCountry(brand.getCountry());

		return brandDTO;
	}

	@PostMapping("/new")
	public Object saveBrand(@RequestBody Brand brand) {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		return brandService.saveBrand(brand);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteBrand(@PathVariable String id) {
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
