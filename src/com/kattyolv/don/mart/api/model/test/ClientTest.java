package com.kattyolv.don.mart.api.model.test;

import com.kattyolv.don.mart.api.model.Client;

public class ClientTest {

	public static void main(String[] args) {
		
		System.out.println("CLIENT");
		
		Client client = new Client();
		client.setId(1);
		client.setName("sherlock");
		client.setEmail("sherlock@gmail.com");
		client.setPassword("988");
		
		if(client.getId() == 1 && 
				client.getName() == "sherlock" &&
				client.getEmail() == "sherlock@gmail.com" &&
				client.getPassword() == "98") {
			
			System.out.println("All nice.");
		}
		else {
			System.out.println("Something went wrong.");
		}
	}

}
