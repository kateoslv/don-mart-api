package com.kattyolv.don.mart.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kattyolv.don.mart.api.jdbc.ConnectionJDBC;
import com.kattyolv.don.mart.api.model.Employee;

public class DAOEmployee {

	private static final String SELECT = "SELECT * FROM employee";
	
	public Connection connection;
	
	public DAOEmployee() {
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		connection = connectionJDBC.getConnection();
	}
	
	public ArrayList<Employee> selectEmployee() {
		
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT);
			
			ResultSet resultSet = statement.executeQuery();
			
			ArrayList<Employee> employees = new ArrayList<Employee>();
		
			while(resultSet.next()) {
				
				Employee employee = new Employee();
				
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPassword(resultSet.getString("password"));
				
				employees.add(employee);
			}
			
			return employees;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
