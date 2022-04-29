package com.se.codingclub.controller;

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

import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.dto.ShoppingSessionDTO;
import com.se.codingclub.entity.ShoppingSession;
import com.se.codingclub.service.ShoppingSessionService;

@CrossOrigin
@RestController
@RequestMapping("/api/shoppingsessions")
public class ShoppingSessionController {

	@Autowired
	private ShoppingSessionService shoppingSessionService;

	@PostMapping("/new")
	public ShoppingSessionDTO saveShoppingSession(@RequestBody ShoppingSession shoppingSession) {
		ShoppingSession cart = shoppingSessionService.saveShoppingSession(shoppingSession);
		ShoppingSessionDTO shoppingSessionDTO = new ShoppingSessionDTO();
		shoppingSessionDTO.setId(cart.getId());
		shoppingSessionDTO.setCreatedDate(cart.getCreatedDate());
		shoppingSessionDTO.setModifiedDate(cart.getModifiedDate());
		shoppingSessionDTO.setUser(cart.getUser());
		return shoppingSessionDTO;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponeMessage> deleteShoppingSession(@PathVariable("id") String id) {
		try {
			shoppingSessionService.deleteShoppingSession(Integer.parseInt(id));
			return ResponseEntity.ok().body(new ResponeMessage("Delete Success!"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Could not Delete!"));
		}

	}

	@GetMapping
	public ShoppingSessionDTO getShoppingSessionByUserId(@RequestParam(required = true) String user_id) {
		ShoppingSession cart = shoppingSessionService.getShoppingSessionByUserId(Integer.parseInt(user_id));
		ShoppingSessionDTO shoppingSessionDTO = new ShoppingSessionDTO();
		shoppingSessionDTO.setId(cart.getId());
		shoppingSessionDTO.setCreatedDate(cart.getCreatedDate());
		shoppingSessionDTO.setModifiedDate(cart.getModifiedDate());
		shoppingSessionDTO.setUser(cart.getUser());
		return shoppingSessionDTO;
	}

}
