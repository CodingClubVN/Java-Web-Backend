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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.se.codingclub.dto.Auth;
import com.se.codingclub.dto.BrandDTO;
import com.se.codingclub.dto.CartDetailDTO;
import com.se.codingclub.dto.CategoryDTO;
import com.se.codingclub.dto.ImageDTO;
import com.se.codingclub.dto.ProductDTO;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.dto.ShoppingSessionDTO;
import com.se.codingclub.entity.CartDetail;
import com.se.codingclub.entity.Image;
import com.se.codingclub.entity.Product;
import com.se.codingclub.entity.ShoppingSession;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.CartDetailService;
import com.se.codingclub.service.ProductService;
import com.se.codingclub.service.ShoppingSessionService;

@CrossOrigin
@RestController
@RequestMapping("/api/cartdetail")
public class CartDetailController {

	@Autowired
	private CartDetailService cartDetailService;
	@Autowired
	private ProductService productService;
	@Autowired
	private Auth tokenWarp;
	@Autowired
	private ShoppingSessionService shoppingSessionService;
	@Autowired
	private AuthService authService;

	@PostMapping("/new")
	public Object saveCartDetail(@RequestBody CartDetail cartDetail) {

		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		ShoppingSession shoppingSession = shoppingSessionService.getShoppingSessionByUserId(user.getId());
		CartDetailDTO cartDetailDTO = new CartDetailDTO();
		cartDetail.setShoppingSession(shoppingSession);
		CartDetail cartDetail2 = cartDetailService.saveCartDetail(cartDetail);
		ShoppingSessionDTO shoppingSessionDTO = new ShoppingSessionDTO();
		shoppingSessionDTO.setId(cartDetail2.getShoppingSession().getId());
		shoppingSessionDTO.setModifiedDate(cartDetail2.getShoppingSession().getModifiedDate());
		shoppingSessionDTO.setCreatedDate(cartDetail2.getShoppingSession().getCreatedDate());
		ProductDTO productDTO = new ProductDTO();
		Map<Product, List<Image>> map = productService.getProductById(cartDetail2.getProduct().getId());
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
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getId(), productTemp.getCategory().getName(),
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
		});
		cartDetailDTO.setId(cartDetail2.getId());
		cartDetailDTO.setShoppingSessionDTO(shoppingSessionDTO);
		cartDetailDTO.setQuantity(cartDetail2.getQuantity());
		cartDetailDTO.setProductDTO(productDTO);
		cartDetailDTO.setModifiedDate(cartDetail2.getModifiedDate());
		cartDetailDTO.setCreatedDate(cartDetail2.getCreatedDate());

		return cartDetailDTO;
	}

	@GetMapping("/{id}")
	public CartDetailDTO getCartDetailById(@PathVariable String id) {
		CartDetailDTO cartDetailDTO = new CartDetailDTO();
		CartDetail cartDetail2 = cartDetailService.getCartDetailById(Integer.parseInt(id));
		ShoppingSessionDTO shoppingSessionDTO = new ShoppingSessionDTO();
		shoppingSessionDTO.setId(cartDetail2.getShoppingSession().getId());
		shoppingSessionDTO.setModifiedDate(cartDetail2.getShoppingSession().getModifiedDate());
		shoppingSessionDTO.setCreatedDate(cartDetail2.getShoppingSession().getCreatedDate());

		ProductDTO productDTO = new ProductDTO();
		Map<Product, List<Image>> map = productService.getProductById(cartDetail2.getProduct().getId());
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
			productDTO.setCategoryDTO(new CategoryDTO(productTemp.getId(), productTemp.getCategory().getName(),
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
		});
		cartDetailDTO.setId(cartDetail2.getId());
		cartDetailDTO.setShoppingSessionDTO(shoppingSessionDTO);
		cartDetailDTO.setQuantity(cartDetail2.getQuantity());
		cartDetailDTO.setProductDTO(productDTO);
		cartDetailDTO.setModifiedDate(cartDetail2.getModifiedDate());
		cartDetailDTO.setCreatedDate(cartDetail2.getCreatedDate());

		return cartDetailDTO;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteCartDetail(@PathVariable String id) {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		try {
			cartDetailService.deleteCartDetail(Integer.parseInt(id));
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success!"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete!"));
		}
	}

	@GetMapping
	public Object getListCartDetail() {
		String token  = tokenWarp.getToken();
		if (token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponeMessage("Please login to continue!"));
		}
		User user = authService.getUserByToken(token);
		ShoppingSession shoppingSession = shoppingSessionService.getShoppingSessionByUserId(user.getId());
		List<CartDetail> cartDetails = cartDetailService
				.getListCartDetailBySessionId(shoppingSession.getId());
		List<CartDetailDTO> detailDTOs = new ArrayList<CartDetailDTO>();
		for (CartDetail cartDetail2 : cartDetails) {
			CartDetailDTO cartDetailDTO = new CartDetailDTO();
			ShoppingSessionDTO shoppingSessionDTO = new ShoppingSessionDTO();
			shoppingSessionDTO.setId(cartDetail2.getShoppingSession().getId());
			shoppingSessionDTO.setModifiedDate(cartDetail2.getShoppingSession().getModifiedDate());
			shoppingSessionDTO.setCreatedDate(cartDetail2.getShoppingSession().getCreatedDate());

			ProductDTO productDTO = new ProductDTO();
			Map<Product, List<Image>> map = productService.getProductById(cartDetail2.getProduct().getId());
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
				productDTO.setCategoryDTO(new CategoryDTO(productTemp.getId(), productTemp.getCategory().getName(),
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
			});
			cartDetailDTO.setId(cartDetail2.getId());
			cartDetailDTO.setShoppingSessionDTO(shoppingSessionDTO);
			cartDetailDTO.setQuantity(cartDetail2.getQuantity());
			cartDetailDTO.setProductDTO(productDTO);
			cartDetailDTO.setModifiedDate(cartDetail2.getModifiedDate());
			cartDetailDTO.setCreatedDate(cartDetail2.getCreatedDate());
			detailDTOs.add(cartDetailDTO);
		}
		return detailDTOs;
	}
}
