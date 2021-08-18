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

import com.model.Product;
import com.service.ProductServiceIntf;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductServiceIntf productService;

	@PostMapping("/saveproduct")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {

		productService.saveProduct(product);

		return new ResponseEntity<String>("product inserted " + product.getId(), HttpStatus.CREATED);

	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> list = productService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("/productById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
		
		Product product = productService.getProductById(id);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
}
