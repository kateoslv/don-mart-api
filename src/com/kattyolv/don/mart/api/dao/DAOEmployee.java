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
	private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM employee WHERE email=? AND password=?";
	private static final String INSERT = "INSERT INTO employee (name, address, email, password) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE employee SET name=?, address=?, password=? WHERE email=?";
	private static final String DELETE = "DELETE FROM employee WHERE id=?";
	
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
				
				employees.add(employee);
			}
			
			return employees;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Employee selectEmployeeByEmailAndPassword(String email, String password) {
		
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD);
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				
				Employee employee = new Employee();
				
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setEmail(resultSet.getString("email"));
				
				return employee;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean insertEmployee(Employee employee) {
		
		boolean wasInserted = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getAddress());
			statement.setString(3, employee.getEmail());
			statement.setString(4, employee.getPassword());
			
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
	
	public boolean updateEmployee(Employee employee) {
		
		boolean wasUpdated = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getAddress());
			statement.setString(3, employee.getPassword());
			statement.setString(4, employee.getEmail());
			
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
	
	public boolean deleteEmployee(int id) {
		
		boolean hasDeleted = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE);
			
			statement.setInt(1, id);
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				hasDeleted = true;
			}			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hasDeleted;
		
	}
	
}
