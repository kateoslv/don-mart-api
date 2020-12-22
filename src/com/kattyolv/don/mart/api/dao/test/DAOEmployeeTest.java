package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOEmployee;
import com.kattyolv.don.mart.api.model.Employee;

public class DAOEmployeeTest {

	public static void main(String[] args) {
		
		DAOEmployeeTest.selectTest();
		DAOEmployeeTest.insertTest();
		DAOEmployeeTest.updateTest();
		DAOEmployeeTest.deleteTest();

	}

	public static void selectTest() {
		
		System.out.println("SELECT EMPLOYEE TEST");
		
		DAOEmployee employeeDAO = new DAOEmployee();
		ArrayList<Employee> employees = employeeDAO.selectEmployee();
		
		for(Employee employee : employees) {
			System.out.println(employee.getName());
		}
		
	}
	
	public static void insertTest() {
		
		System.out.println("INSERT EMPLOYEE TEST");
		
		DAOEmployee employeeDAO = new DAOEmployee();

		Employee employee = new Employee();
		
		employee.setName("carol");
		employee.setAddress("green gable");
		employee.setEmail("anne@gmail.com");
		employee.setPassword("333");
		
		boolean wasInserted = employeeDAO.insertEmployee(employee);
		
		if(wasInserted == true) {
			System.out.println("Employee was inserted successfully.");
		}
		else {
			System.out.println("Fail to insert.");
		}
	}
	
	public static void updateTest() {
		
		System.out.println("UPDATE EMPLOYEE TEST");
		
		DAOEmployee employeeDAO = new DAOEmployee();
		
		Employee employee = new Employee();
		
		employee.setName("anne");
		employee.setAddress("green gables");
		employee.setEmail("anne@gmail.com");
		employee.setPassword("900");
		
		boolean wasUpdated = employeeDAO.updateEmployee(employee);
		
		if(wasUpdated == true) {
			System.out.println("Employee updated successfully.");
		}
		else {
			System.out.println("Fail to update.");
		}
	}
	
	public static void deleteTest() {

		System.out.println("DELETE EMPLOYEE TEST");
		
		DAOEmployee employeeDAO = new DAOEmployee();
		
		boolean hasDeleted = employeeDAO.deleteEmployee(5);
		
		if(hasDeleted == true) {
			System.out.println("Employee deleted successfully.");
		}
		else {
			System.out.println("Fail to delete.");	
		}
	}
	
}
