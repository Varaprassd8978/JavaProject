package com.service;

import java.util.List;
import java.util.Optional;

import com.model.Product;

public interface ProductServiceIntf {

	void saveProduct(Product product);

	List<Product> getAllProducts();

	Product getProductById(int id);

}
