package com.service;

import java.util.List;

import com.model.Order;

public interface OrderServiceIntf {

	void saveOrder(Order order);

	List<Order> getAllOrders();

	Order getOrderById(int id);

}
