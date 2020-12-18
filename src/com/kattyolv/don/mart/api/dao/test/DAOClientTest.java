package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOClient;
import com.kattyolv.don.mart.api.model.Client;

public class DAOClientTest {

	public static void main(String[] args) {
		
		DAOClientTest.selectTest();

	}
	
	public static void selectTest() {
		
		System.out.println("SELECT CLIENT TEST");
		
		DAOClient clientDAO = new DAOClient();
		ArrayList<Client> clients = clientDAO.selectClient();
		
		for(Client client : clients) {
			System.out.println("Id: " + client.getId());
			System.out.println("Name: " + client.getName());
			System.out.println("Address: " + client.getAddress());
			System.out.println("Email: " + client.getEmail());
			System.out.println("Password: " + client.getPassword());
			
		}
		
	}

}
