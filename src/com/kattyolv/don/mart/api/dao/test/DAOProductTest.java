package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOProduct;
import com.kattyolv.don.mart.api.model.Product;

public class DAOProductTest {

	public static void main(String[] args) {
		
		DAOProductTest.selectTest();
		DAOProductTest.insertTest();
		DAOProductTest.updateTest();
		DAOProductTest.deleteTest();

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
	
	public static void updateTest() {
		
		System.out.println("UPDATE PRODUCT TEST");
		
		DAOProduct productDAO = new DAOProduct();
		Product product = new Product();
		
		product.setName("bread");
		product.setPrice(0.85);
		product.setId(3);
		
		boolean wasUpdated = productDAO.updateProduct(product);
		
		if(wasUpdated == true) {
			System.out.println("Product updated successfully.");
		}
		else {
			System.out.println("Fail to update product.");
		}
		
	}
	
	public static void deleteTest() {
		
		System.out.println("DELETE PRODUCT TEST");
		
		DAOProduct productDAO = new DAOProduct();
		
		boolean wasDeleted = productDAO.deleteProduct(6);
		
		if(wasDeleted == true) {
			System.out.println("Product deleted successfully.");
		}
		else {
			System.out.println("Fail to delete product.");
		}
	}

}
