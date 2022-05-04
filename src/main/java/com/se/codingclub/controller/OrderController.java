package com.se.codingclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.codingclub.dto.Auth;
import com.se.codingclub.dto.ResponeMessage;
import com.se.codingclub.entity.Order;
import com.se.codingclub.entity.OrderDetail;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;
import com.se.codingclub.service.OrderDetailService;
import com.se.codingclub.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private Auth tokenAuth;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@PostMapping("/new")
	public Object createOrder(@RequestBody Order order) {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user = authService.getUserByToken(token);
			order.setUser(user);
			Order order_1 = order;
			List<OrderDetail> list = order.getOrderDetails();
			order_1.setOrderDetails(null);
			Order orderNew  = orderService.createOrder(order_1);
			for(OrderDetail item: list) {
				item.setOrder(orderNew);
				OrderDetail orderDetail=  orderDetailService.createOrderDetail(item);
			}
			return ResponseEntity.status(200).body(new ResponeMessage("create order success!"));
		}
	}
	
	@PutMapping("/{id}")
	public Object updateStatusOrder(@PathVariable String id, @RequestBody Order order) {
		System.out.println(id);
		System.out.println(order.getStatus());
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			Order orderNew = orderService.updateStatusOrder(Integer.parseInt(id), order.getStatus());
			return ResponseEntity.status(200).body(orderNew);
		}
	}
	
	@GetMapping("/{id}")
	public Object getOrderById(@PathVariable String id) {
		return orderService.getOrderById(Integer.parseInt(id));
	}

}
