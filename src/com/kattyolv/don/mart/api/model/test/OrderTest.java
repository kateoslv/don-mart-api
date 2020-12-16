package com.kattyolv.don.mart.api.model.test;

import com.kattyolv.don.mart.api.model.Order;
import com.kattyolv.don.mart.api.model.Client;

public class OrderTest {

	public static void main(String[] args) {
		
		System.out.println("ORDER");
		
		Order order = new Order();
		order.setId(1);
		order.setItensQuantity(5);
		order.setTotalPrice(33);
		
		Client client = new Client();
		client.setId(2);
		client.setName("chandler");
		client.setEmail("chandler@gmail.com");
		client.setPassword("556");
		
		order.setClient(client);
		
		if(order.getId() == 1 && 
				order.getItensQuantity() == 5 &&
				order.getTotalPrice() == 33 &&
				order.getClient().getId() == 2 && 
				order.getClient().getName() == "chandler" &&
				order.getClient().getEmail() == "chandler@gmail.com" &&
				order.getClient().getPassword() == "556") {
			
			System.out.println("All nice.");
		}
		else {
			System.out.println("Something went wrong.");
		}
	}

}
