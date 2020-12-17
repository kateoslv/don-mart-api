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
	private static final String INSERT = "INSERT INTO products (name, price) VALUES (?,?)";
	private static final String UPDATE = "UPDATE products SET name=?, price=? WHERE id=?";

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
	
	public boolean insertProduct(Product product) {
		
		boolean wasInserted = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				wasInserted = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasInserted;

	}
	
	public boolean updateProduct(Product product) {
		
		boolean wasUpdated = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setInt(3, product.getId());
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				wasUpdated = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wasUpdated;
		
	}
	
}
