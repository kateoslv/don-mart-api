package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOOrder;
import com.kattyolv.don.mart.api.model.Order;

public class DAOOrderTest {

	public static void main(String[] args) {
		
		DAOOrderTest.selectTest();

	}
	
	public static void selectTest() {
		
		System.out.println("SELECT ORDER TEST");
		
		DAOOrder orderDAO = new DAOOrder();
		ArrayList<Order> orders = orderDAO.selectOrder();
		
		for(Order order : orders) {
			System.out.println("id: " + order.getId());
			System.out.println("id client: " + order.getClient().getId());
			System.out.println("name client: " + order.getClient().getName());
			System.out.println("itens quantity: " + order.getItensQuantity());
			System.out.println("total price: " + order.getTotalPrice());
		}
		
	}

}
