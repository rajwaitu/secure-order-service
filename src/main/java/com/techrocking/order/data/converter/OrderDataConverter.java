package com.techrocking.order.data.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techrocking.order.entity.Order;
import com.techrocking.order.payload.GetOrderResponse;
import com.techrocking.order.payload.OrderPayload;

@Component
public class OrderDataConverter {
	
	public GetOrderResponse convert(List<Order> orderEntities) {
		GetOrderResponse response = new GetOrderResponse();
		List<OrderPayload> orders = new ArrayList<>();
		
		orderEntities.forEach(entity -> {
			OrderPayload orderPayload = new OrderPayload();
			orderPayload.setOrderId(entity.getId());
			//orderPayload.setCustomerName(entity.getCustomerName());
			//orderPayload.setCustomerPhone(entity.getCustomerPhone());
			orderPayload.setItemName(entity.getItemName());
			orderPayload.setItemCategory(entity.getItemCategory());
			orderPayload.setItemSubcategory(entity.getItemSubcategory());
			orderPayload.setQuantity(entity.getQuantity());
			//orderPayload.setCreationDate(entity.getCreationDate());
			orders.add(orderPayload);
		});
		
		response.setOrders(orders);
		return response;
		
	}

}
