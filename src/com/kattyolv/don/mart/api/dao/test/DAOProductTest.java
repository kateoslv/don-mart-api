package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOProduct;
import com.kattyolv.don.mart.api.model.Product;

public class DAOProductTest {

	public static void main(String[] args) {
		
		DAOProductTest.selectTest();
		DAOProductTest.insertTest();

	}
	
	public static void selectTest() {
		
		System.out.println("SELECT PRODUCT TEST");
		
		DAOProduct productDAO = new DAOProduct();
		ArrayList<Product> products = productDAO.selectProduct();
		
		for(Product product : products) {
			System.out.println("product name: " + product.getName());
		}
		
	}
	
	public static void insertTest() {
		
		System.out.println("INSERT PRODUCT TEST");
		
		DAOProduct productDAO = new DAOProduct();
		Product product = new Product();
		
		product.setName("bread");
		product.setPrice(0.90);
		
		boolean wasInserted = productDAO.insertProduct(product);
		
		if(wasInserted == true) {
			System.out.println("Product inserted successfully.");
		}
		else {
			System.out.println("Fail to insert product.");
		}
		
	}

}
