package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOProduct;
import com.kattyolv.don.mart.api.model.Product;

public class DAOProductTest {

	public static void main(String[] args) {
		
		DAOProductTest.selectTest();

	}
	
	public static void selectTest() {
		
		System.out.println("SELECT PRODUCT TEST");
		
		DAOProduct productDAO = new DAOProduct();
		ArrayList<Product> products = productDAO.selectProduct();
		
		for(Product product : products) {
			System.out.println("product name: " + product.getName());
		}
		
	}

}
