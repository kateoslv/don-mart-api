package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOClient;
import com.kattyolv.don.mart.api.model.Client;

public class DAOClientTest {

	public static void main(String[] args) {
		
		DAOClientTest.selectTest();
		DAOClientTest.selectByEmailAndPassword();
		DAOClientTest.insertClient();
		DAOClientTest.updateClient();
		
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
		}
		
	}

	public static void selectByEmailAndPassword() {
		
		System.out.println("SELECT BY EMAIL AND PASSWORD TEST");
		
		DAOClient clientDAO = new DAOClient();
		Client client = clientDAO.selectClientByEmailAndPassword("rachel@gmail.com", "234");
		
		if(client!= null) {
			System.out.println("Id: " + client.getId());
			System.out.println("Name: " + client.getName());
			System.out.println("Address: " + client.getAddress());
			System.out.println("Email: " + client.getEmail());
		}
		else {
			System.out.println("Client does not exist.");
		}
		
	}
	
	public static void insertClient() {
		
		System.out.println("INSERT CLIENT TEST");
		
		DAOClient clientDAO = new DAOClient();
		
		Client client = new Client();
		
		client.setName("monica");
		client.setAddress("NY");
		client.setEmail("monica@gmail.com");
		client.setPassword("344");
		
		boolean wasInserted = clientDAO.insertClient(client);
		
		if(wasInserted == true) {
			System.out.println("Client inserted successfully.");
		}
		else {
			System.out.println("Fail to insert.");
		}
		
	}
	
	public static void updateClient() {
		
		System.out.println("UPDATE CLIENT TEST");
		
		DAOClient clientDAO = new DAOClient();
		Client client = new Client();
		
		client.setName("phoebe");
		client.setAddress("NY");
		client.setPassword("678");
		client.setEmail("phoebe@gmail.com");
		
		boolean wasUpdated = clientDAO.updateClient(client);
		
		if(wasUpdated) {
			System.out.println("Update successfully.");
		}
		else {
			System.out.println("Fail to update.");
		}
		
	}
	
}
