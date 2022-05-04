package com.se.codingclub.controller;

import java.util.ArrayList;
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
import com.se.codingclub.dto.OrderDTO;
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
	public Object createOrder(@RequestBody OrderDTO order) {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user = authService.getUserByToken(token);
			Order order_1 = new Order(user, order.getTotal(), order.getStatus(), order.getAddress(),order.getCreateDate(), order.getModifiedDate());
			List<OrderDetail> list = order.getOrderDetails();
			List<OrderDetail> listNew = new ArrayList<OrderDetail>();
			Order orderNew  = orderService.createOrder(order_1);
			for(OrderDetail item: list) {
				item.setOrder(orderNew);
				OrderDetail orderDetail=  orderDetailService.createOrderDetail(item);
				listNew.add(orderDetail);
			}
			order.setId(orderNew.getId());
			order.setUser(user);
			order.setCreateDate(orderNew.getCreateDate());
			order.setModifiedDate(orderNew.getModifiedDate());
			order.setOrderDetails(listNew);
			return ResponseEntity.status(200).body(order);
		}
	}
	
	@PutMapping("/updateStatus/{id}")
	public Object updateStatusOrder(@PathVariable String id, @RequestBody OrderDTO order) {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user  = authService.getUserByToken(token);
			if (user.getRole().equals("admin")) {
				Order orderNew = orderService.updateStatusOrder(Integer.parseInt(id), order.getStatus());
				return ResponseEntity.status(200).body(new ResponeMessage("Update status success!"));
			}else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
			}
		}
	}
	
	@GetMapping("/detail/{id}")
	public Object getOrderById(@PathVariable String id) {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			Order order = orderService.getOrderById(Integer.parseInt(id));
			List<OrderDetail> listOrderDetail = orderDetailService.getOrderDetailByOrderId(Integer.parseInt(id));
			OrderDTO orDto = new OrderDTO();
			orDto.setId(order.getId());
			orDto.setUser(order.getUser());
			orDto.setAddress(order.getAddress());
			orDto.setStatus(order.getStatus());
			orDto.setCreateDate(order.getCreateDate());
			orDto.setModifiedDate(order.getModifiedDate());
			orDto.setTotal(order.getTotal());
			orDto.setOrderDetails(listOrderDetail);
			return ResponseEntity.status(200).body(orDto);
		}
	}
	
	@GetMapping("/list")
	public Object getOrders() {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user  = authService.getUserByToken(token);
			if (user.getRole().equals("admin")) {
				List<Order> list = orderService.getOrders();
				return ResponseEntity.status(200).body(list);
			}else {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Account does not have permission to perform this function!"));
			}
		}
	}
	@GetMapping("/me")
	public Object getOrderOfUser() {
		String token = tokenAuth.getToken();
		if(token == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeMessage("Please login to continue!"));
		}else {
			User user = authService.getUserByToken(token);
			List<Order> list = orderService.getOrdersByUserId(user.getId());
			return ResponseEntity.status(200).body(list);
		}
	}

}
