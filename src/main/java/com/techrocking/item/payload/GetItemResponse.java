package com.techrocking.item.payload;

import java.util.List;

public class GetItemResponse {
	
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
