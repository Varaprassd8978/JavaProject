package com.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.model.Product;

@FeignClient(name ="product-service", url = "http://localhost:8091/")
public interface ProductFeignClient {

	@GetMapping("/product/productById/{id}")
	public Product getProductById(@PathVariable("id") int productId);
}