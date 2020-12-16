package com.kattyolv.don.mart.api.model.test;

import com.kattyolv.don.mart.api.model.Product;

public class ProductTest {

	public static void main(String[] args) {
		
		System.out.println("PRODUCT");
		
		Product product = new Product();
		product.setId(1);
		product.setName("cake");
		product.setPrice(9);
		
		if(product.getId() == 1 && 
				product.getName() == "cake" &&
				product.getPrice() == 9) {
			
			System.out.println("All nice.");
		}
		else {
			System.out.println("Something it's different.");
		}
	}

}
