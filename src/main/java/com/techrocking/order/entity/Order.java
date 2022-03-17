package com.techrocking.order.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_category")
	private String itemCategory;
	
	@Column(name = "item_subcategory")
	private String itemSubcategory;
	
	@Column(name = "quantity")
	private Long quantity;

	/*
	 * @Column(name = "creationDate") private LocalDateTime creationDate;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSubcategory() {
		return itemSubcategory;
	}

	public void setItemSubcategory(String itemSubcategory) {
		this.itemSubcategory = itemSubcategory;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/*
	 * public LocalDateTime getCreationDate() { return creationDate; }
	 * 
	 * public void setCreationDate(LocalDateTime creationDate) { this.creationDate =
	 * creationDate; }
	 */

	

}
