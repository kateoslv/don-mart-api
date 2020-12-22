package com.kattyolv.don.mart.api.dao.test;

import java.util.ArrayList;

import com.kattyolv.don.mart.api.dao.DAOEmployee;
import com.kattyolv.don.mart.api.model.Employee;

public class DAOEmployeeTest {

	public static void main(String[] args) {
		
		DAOEmployeeTest.selectTest();

	}

	public static void selectTest() {
		
		System.out.println("SELECT EMPLOYEE TEST");
		
		DAOEmployee employeeDAO = new DAOEmployee();
		ArrayList<Employee> employees = employeeDAO.selectEmployee();
		
		for(Employee employee : employees) {
			System.out.println(employee.getName());
		}
		
	}
	
}
