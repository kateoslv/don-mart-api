package com.kattyolv.don.mart.api.model;

public class Order {

	private int id;
	private int itensQuantity;
	private double totalPrice;
	private Client client;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getItensQuantity() {
		return this.itensQuantity;
	}
	
	public void setItensQuantity(int itensQuantity) {
		this.itensQuantity = itensQuantity;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Client getClient() {
		return this.client;
	}
	
	public void setClient(Client idClient) {
		this.client = idClient;
	}

}
