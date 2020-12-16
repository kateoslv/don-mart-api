package com.kattyolv.don.mart.api.model;

public class OrderProducts {

	private int id;
	private Order order;
	private Product product;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public void setOrder(Order idOrder) {
		this.order = idOrder;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product idProduct) {
		this.product = idProduct;
	}
	
}
