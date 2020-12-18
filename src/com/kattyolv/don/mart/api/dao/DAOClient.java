package com.kattyolv.don.mart.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.don.mart.api.jdbc.ConnectionJDBC;
import com.kattyolv.don.mart.api.model.Client;

public class DAOClient {

	private static final String SELECT = "SELECT * FROM client";
	private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM client WHERE email=? AND password=?";
	
	private Connection connection;
	
	public DAOClient() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public ArrayList<Client> selectClient() {
		
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT);		
				
			ResultSet resultSet = statement.executeQuery();
			
			ArrayList<Client> clients = new ArrayList<Client>();
						
			while(resultSet.next()) {
				
				Client client = new Client();
				
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));
				client.setAddress(resultSet.getString("address"));
				client.setEmail(resultSet.getString("email"));
				
				clients.add(client);
			}
			
			return clients;			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Client selectClientByEmailAndPassword(String email, String password) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD);
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				Client client = new Client();
				
				client.setId(resultSet.getInt("id"));
				client.setName(resultSet.getString("name"));
				client.setAddress(resultSet.getString("address"));
				client.setEmail(resultSet.getString("email"));
				
				return client;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
