package com.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.model.Order;

@FeignClient(name ="order-service", url = "http://localhost:8092/")
public interface OrderFeignClient {

	@GetMapping("/order/orderById/{id}")
	public Order getOrderById(@PathVariable("id") int orderId);
}