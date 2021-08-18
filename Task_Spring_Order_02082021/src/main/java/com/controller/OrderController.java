package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Order;
import com.model.Product;
import com.service.OrderServiceIntf;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderServiceIntf orderService;

	@Autowired
	private ProductFeignClient productClient;

	@PostMapping("/saveorder")
	public ResponseEntity<String> saveOrder(@RequestBody Order order) {

		int productId = order.getProductId();
		Product product = productClient.getProductById(productId);

		System.out.println(product);
		if (product != null) {
			orderService.saveOrder(order);
			return new ResponseEntity<String>("order inserted " + order.getOrderId(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("No Product! check the product Id ", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> list = orderService.getAllOrders();

		return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
	}

	@GetMapping("/orderById/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {

		Order order = orderService.getOrderById(id);

		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}
