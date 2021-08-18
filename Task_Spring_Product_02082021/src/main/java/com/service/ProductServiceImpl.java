package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Product;
import com.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductServiceIntf {

	@Autowired
	ProductRepository repo;

	public void saveProduct(Product product) {
		repo.save(product);
	}

	public List<Product> getAllProducts() {

		return repo.findAll();
	}

	public Product getProductById(int id) {
		return repo.findById(id).get();

	}

}
