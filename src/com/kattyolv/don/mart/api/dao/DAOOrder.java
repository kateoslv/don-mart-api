package com.kattyolv.don.mart.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.don.mart.api.jdbc.ConnectionJDBC;
import com.kattyolv.don.mart.api.model.Client;
import com.kattyolv.don.mart.api.model.Order;
import com.kattyolv.don.mart.api.model.Product;

public class DAOOrder {
	
	private static final String SELECT = "SELECT o.id, o.id_client, o.itens_quantity, o.total_price, c.name "
			+ "FROM `order` o INNER JOIN client c ON c.id = o.id_client";
	private static final String SELECT_ORDER_PRODUCTS = "SELECT o_p.id, o_p.id_order, o_p.id_product, p.name AS product "
			+ "FROM order_products o_p INNER JOIN products p ON p.id = o_p.id_product WHERE o_p.id_order = ?";
	
	
	Connection connection;
	
	public DAOOrder() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public ArrayList<Order> selectOrder() {
		
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT);
			PreparedStatement statementOrderProducts = connection.prepareStatement(SELECT_ORDER_PRODUCTS);
			
			ResultSet resultSet = statement.executeQuery();
			
			ArrayList<Order> orders = new ArrayList<Order>();
			ArrayList<Product> orderProducts = new ArrayList<Product>();
			
			while(resultSet.next()) {
				
				Client client = new Client();
				client.setId(resultSet.getInt("id_client"));
				client.setName(resultSet.getString("name"));

				Order order = new Order();
				
				order.setId(resultSet.getInt("id"));
				order.setClient(client);
				order.setItensQuantity(resultSet.getInt("itens_quantity"));
				order.setTotalPrice(resultSet.getDouble("total_price"));
				
				statementOrderProducts.setInt(1, order.getId());
				
				ResultSet resultSetOrderProducts = statementOrderProducts.executeQuery();
				
				while(resultSetOrderProducts.next()) {
					
					Product product = new Product();
					
					product.setName(resultSetOrderProducts.getString("product"));
					
				}
				
				orders.add(order);
			}
			
			return orders;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
