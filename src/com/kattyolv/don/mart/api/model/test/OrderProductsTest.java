package com.kattyolv.don.mart.api.model.test;

import com.kattyolv.don.mart.api.model.Client;
import com.kattyolv.don.mart.api.model.Order;
import com.kattyolv.don.mart.api.model.OrderProducts;
import com.kattyolv.don.mart.api.model.Product;

public class OrderProductsTest {

	public static void main(String[] args) {
	
		System.out.println("ORDER PRODUCTS");
		
		OrderProducts orderProducts = new OrderProducts();
		orderProducts.setId(8);
				
		Order order = new Order();
		order.setId(3);
		order.setItensQuantity(3);
		order.setTotalPrice(9);
		
		Client client = new Client();
		client.setId(1);
		client.setName("ross");
		client.setEmail("ross@gmail.com");
		client.setPassword("221");
		
		order.setClient(client);
		
		orderProducts.setOrder(order);
		
		Product product = new Product();
		product.setId(3);
		product.setName("icecream");
		product.setPrice(1.5);
		
		orderProducts.setProduct(product);
		
		if(orderProducts.getId() == 8 &&
				orderProducts.getOrder().getId() == 3 &&
				orderProducts.getOrder().getItensQuantity() == 3 &&
				orderProducts.getOrder().getTotalPrice() == 9 &&
				order.getClient().getId() == 1 && 
				order.getClient().getName() == "ross" && 
				order.getClient().getEmail() == "ross@gmail.com" && 
				order.getClient().getPassword() == "221" && 
				orderProducts.getProduct().getId() == 3 &&
				orderProducts.getProduct().getName() == "icecream" &&
				orderProducts.getProduct().getPrice() == 1.5) {
			
			System.out.println("All nice.");
		}
		else {
			System.out.println("Something went wrong.");
		}
	}

}
