package com.techrocking.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techrocking.customer.payload.CustomerResponsePayload;
import com.techrocking.item.payload.GetItemResponse;
import com.techrocking.item.payload.Item;
import com.techrocking.order.entity.Order;
import com.techrocking.order.payload.CreateOrderRequest;
import com.techrocking.order.repository.OrderRepository;
import com.techrocking.order.rest.util.RestUtil;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestUtil RestUtil;
	
	public Order createOrder(CreateOrderRequest request) {
		Order order = new Order();
		
		//CustomerResponsePayload customerDetails = RestUtil.getCustomerDetailsByDiscoveryClient(request.getCustomerId()) ;
		//CustomerResponsePayload customerDetails = RestUtil.getCustomerDetailsByRibbonBackedClient(request.getCustomerId()) ;
		
		//order.setCustomerName(customerDetails.getName());
		//order.setCustomerPhone(customerDetails.getPhone());
		
		//GetItemResponse itemDetails = RestUtilgetItemDetailsByDiscoveryClient(request.getItemId());
		GetItemResponse itemDetails = RestUtil.getItemDetailsByRibbonBackedClient(request.getItemId());
		Item item = itemDetails.getItems().get(0);
		
		order.setItemName(item.getName());
		order.setItemCategory(item.getCategory());
		order.setItemSubcategory(item.getSubCategory());
		order.setQuantity(item.getQuantity());
		
		//order.setCreationDate(LocalDateTime.now());
		
		return orderRepository.save(order);
	}
	
	public Order getOrder(Long id) {
		return orderRepository.findById(id).get();
	}
	
	public List<Order> getOrder() {
		List<Order> allOrders = new ArrayList<Order>();
		Iterable<Order> itr = orderRepository.findAll();
		itr.forEach(ord -> allOrders.add(ord));
		return allOrders;
	}
	

}
