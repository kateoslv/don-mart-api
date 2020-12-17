package com.kattyolv.don.mart.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.don.mart.api.jdbc.ConnectionJDBC;
import com.kattyolv.don.mart.api.model.Product;

public class DAOProduct {

	private static final String SELECT = "SELECT * FROM products";

	private Connection connection;
	
	public DAOProduct() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public ArrayList<Product> selectProduct() {
		
		try {
			ArrayList<Product> products = new ArrayList<Product>();
			
			PreparedStatement statement = connection.prepareStatement(SELECT);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Product product = new Product();
				
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getDouble("price"));
				
				products.add(product);
				
			}

			return products;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
