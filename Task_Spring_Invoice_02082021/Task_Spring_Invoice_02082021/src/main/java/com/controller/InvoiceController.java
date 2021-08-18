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

import com.model.Invoice;
import com.model.Order;
import com.model.Product;
import com.service.InvoiceServiceIntf;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	InvoiceServiceIntf invService;

	@Autowired
	private ProductFeignClient productClient;

	@Autowired
	private OrderFeignClient orderClient;

	@PostMapping("/save")
	public ResponseEntity<String> saveInvoice(@RequestBody Invoice model) {

		int productId = model.getProductId();
		int orderId = model.getOrderId();

		Product product = productClient.getProductById(productId);
		System.out.println(product);
		Order order = orderClient.getOrderById(orderId);
		System.out.println(order);

		String orderStatus = order.getStatus();
		
		double productPrice = product.getPrice();
		int quantity = order.getQuantity();
		
		if (orderStatus.equals("active")) {			
			
			if (product.equals(null) && order.equals(null)) {
				return new ResponseEntity<String>("Unable to generate Invoice.check productId and OrderId!",
						HttpStatus.BAD_REQUEST);
			} else {

				double totalOrderValue = productPrice * quantity;
				model.setTotal(totalOrderValue);
				
				invService.saveInvoice(model);

				return new ResponseEntity<String>("Invoice genreated " + model.getInvoiceId(), HttpStatus.CREATED);

			}
		} else {
			return new ResponseEntity<String>("Unable to generate Invoice.Since order status is InA	ctive",
					HttpStatus.OK);
		}

	}

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		List<Invoice> list = invService.getAllInvoices();

		return new ResponseEntity<List<Invoice>>(list, HttpStatus.OK);
	}

	@GetMapping("/invoiceById/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") int id) {

		Invoice invoice = invService.getInvoiceById(id);

		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

}
