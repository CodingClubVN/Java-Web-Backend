package com.se.codingclub.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.Branch;
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
import com.se.codingclub.dto.CategoryDTO;
import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.dto.ProductDTO;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Brand;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.BrandService;
import com.se.codingclub.service.ImageService;
import com.se.codingclub.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService baBrandService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private Auth tokenWarp;
	@Autowired
	private AuthService authService;

//	@GetMapping("/list")
//	public List<Product> getProducts() {
//		List<Product> products = productService.getListProduct();
//		return products;
//	}

	@GetMapping("/list")
	public ResponseEntity<List<ProductDTO>> getProducts() throws InterruptedException {
		Map<Product, List<Image>> map = productService.getListProduct();
		if (map == null) {
			map = productService.getListProduct();
		}
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		map.entrySet().forEach(product -> {
			Product productTemp = product.getKey();
			List<Image> images = product.getValue();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setUpdatedDate(productTemp.getUpdatedDate());
			productDTO.setPrice(productTemp.getPrice());
			productDTO.setName(productTemp.getName());
			productDTO.setId(productTemp.getId());
			productDTO.setFuelType(productTemp.getFuelType());
			productDTO.setCreatedDate(productTemp.getCreatedDate());
			productDTO.setBodyType(productTemp.getBodyType());
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getCategory().getId(), productTemp.getCategory().getName(),
					productTemp.getCategory().getDescription()));
			productDTO.setBrandDTO(new BrandDTO(productTemp.getBrand().getId(), productTemp.getBrand().getName(),
					productTemp.getBrand().getCountry(), productTemp.getBrand().getFounderYear(),
					productTemp.getBrand().getDescription()));
			List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
			for (Image image : images) {
				ImageDTO imageDTO = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
						.path(image.getId() + "").toUriString();
				imageDTO.setId(image.getId());
				imageDTO.setUrl(url);
				imageDTO.setType(image.getType());
				imageDTOs.add(imageDTO);
			}
			productDTO.setImageDTOs(imageDTOs);
			productDTOs.add(productDTO);
		});
		return ResponseEntity.ok().body(productDTOs);
	}

	@PostMapping("/new")
	public Object saveProduct(@RequestBody Product product) {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		product.setStatus("enable");
		return productService.saveProdcut(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteProduct(@PathVariable String id) {
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

			Product product = new Product();
			product.setStatus("disabled");
			productService.updateProdcut(Integer.parseInt(id), product);
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success"));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete"));
		}
	}

	@PutMapping("/{id}")
	public Object updateProduct(@RequestBody Product product, @PathVariable String id) {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		if(user.getRole().equals("admin") == false) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
		}
		return productService.updateProdcut(Integer.parseInt(id), product);
	}

	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable String id) {
		ProductDTO productDTO = new ProductDTO();
		Map<Product, List<Image>> map = productService.getProductById(Integer.parseInt(id));
		map.entrySet().forEach(entry -> {
			Product productTemp = entry.getKey();
			List<Image> images = entry.getValue();
			productDTO.setUpdatedDate(productTemp.getUpdatedDate());
			productDTO.setPrice(productTemp.getPrice());
			productDTO.setName(productTemp.getName());
			productDTO.setId(productTemp.getId());
			productDTO.setFuelType(productTemp.getFuelType());
			productDTO.setCreatedDate(productTemp.getCreatedDate());
			productDTO.setBodyType(productTemp.getBodyType());
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getCategory().getId(), productTemp.getCategory().getName(),
					productTemp.getCategory().getDescription()));
			Brand brand = baBrandService.getBrandById(productTemp.getBrand().getId());
			List<Image> image_brand = imageService.getListImageBrandById(productTemp.getBrand().getId());
			BrandDTO brandDTO = new BrandDTO();
			List<ImageDTO> imageDTOs_brand = new ArrayList<ImageDTO>();
			brandDTO.setName(brand.getName());
			for (Image image : image_brand) {
				ImageDTO imageDTO_brand = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/").path(image.getId() + "")
						.toUriString();
				imageDTO_brand.setId(image.getId());
				imageDTO_brand.setUrl(url);
				imageDTO_brand.setType(image.getType());
				imageDTOs_brand.add(imageDTO_brand);
			}
			brandDTO.setImageDTOs(imageDTOs_brand);
			brandDTO.setId(brand.getId());
			brandDTO.setFounderYear(brand.getFounderYear());
			brandDTO.setDescription(brand.getDescription());
			brandDTO.setCountry(brand.getCountry());
			productDTO.setBrandDTO(brandDTO);
			
			List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
			for (Image image : images) {
				ImageDTO imageDTO = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
						.path(image.getId() + "").toUriString();
				imageDTO.setId(image.getId());
				imageDTO.setUrl(url);
				imageDTO.setType(image.getType());
				imageDTOs.add(imageDTO);
			}
			productDTO.setImageDTOs(imageDTOs);
		});
		return productDTO;
	}

	@GetMapping("/brand/{brand_id}")
	public Object getProductByBrand(@PathVariable String brand_id) {
		Map<Product, List<Image>> map = productService.getListProductByBrand(Integer.parseInt(brand_id));
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		map.entrySet().forEach(product -> {
			Product productTemp = product.getKey();
			List<Image> images = product.getValue();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setUpdatedDate(productTemp.getUpdatedDate());
			productDTO.setPrice(productTemp.getPrice());
			productDTO.setName(productTemp.getName());
			productDTO.setId(productTemp.getId());
			productDTO.setFuelType(productTemp.getFuelType());
			productDTO.setCreatedDate(productTemp.getCreatedDate());
			productDTO.setBodyType(productTemp.getBodyType());
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getCategory().getId(), productTemp.getCategory().getName(),
					productTemp.getCategory().getDescription()));
			productDTO.setBrandDTO(new BrandDTO(productTemp.getBrand().getId(), productTemp.getBrand().getName(),
					productTemp.getBrand().getCountry(), productTemp.getBrand().getFounderYear(),
					productTemp.getBrand().getDescription()));
			List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
			for (Image image : images) {
				ImageDTO imageDTO = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
						.path(image.getId() + "").toUriString();
				imageDTO.setId(image.getId());
				imageDTO.setUrl(url);
				imageDTO.setType(image.getType());
				imageDTOs.add(imageDTO);
			}
			productDTO.setImageDTOs(imageDTOs);
			productDTOs.add(productDTO);
		});
		return ResponseEntity.status(200).body(productDTOs);
	}
	@GetMapping("/category/{category_id}")
	public Object getProductsByCategory(@PathVariable String category_id) {
		Map<Product, List<Image>> map = productService.getListproductByCategory(Integer.parseInt(category_id));
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		map.entrySet().forEach(product -> {
			Product productTemp = product.getKey();
			List<Image> images = product.getValue();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setUpdatedDate(productTemp.getUpdatedDate());
			productDTO.setPrice(productTemp.getPrice());
			productDTO.setName(productTemp.getName());
			productDTO.setId(productTemp.getId());
			productDTO.setFuelType(productTemp.getFuelType());
			productDTO.setCreatedDate(productTemp.getCreatedDate());
			productDTO.setBodyType(productTemp.getBodyType());
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getCategory().getId(), productTemp.getCategory().getName(),
					productTemp.getCategory().getDescription()));
			productDTO.setBrandDTO(new BrandDTO(productTemp.getBrand().getId(), productTemp.getBrand().getName(),
					productTemp.getBrand().getCountry(), productTemp.getBrand().getFounderYear(),
					productTemp.getBrand().getDescription()));
			List<ImageDTO> imageDTOs = new ArrayList<ImageDTO>();
			for (Image image : images) {
				ImageDTO imageDTO = new ImageDTO();
				String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/images/")
						.path(image.getId() + "").toUriString();
				imageDTO.setId(image.getId());
				imageDTO.setUrl(url);
				imageDTO.setType(image.getType());
				imageDTOs.add(imageDTO);
			}
			productDTO.setImageDTOs(imageDTOs);
			productDTOs.add(productDTO);
		});
		return ResponseEntity.status(200).body(productDTOs);
	}
}
