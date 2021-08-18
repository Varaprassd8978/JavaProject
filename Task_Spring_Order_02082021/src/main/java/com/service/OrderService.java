package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Order;
import com.repository.OrderRepository;
@Service
@Transactional
public class OrderService implements OrderServiceIntf {

	@Autowired
	OrderRepository repo;

	public void saveOrder(Order order) {

		repo.save(order);
	}

	public List<Order> getAllOrders() {

		return repo.findAll();
	}

	public Order getOrderById(int id) {

		return repo.findById(id).get();
	}

}
