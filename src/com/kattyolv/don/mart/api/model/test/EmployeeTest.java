package com.kattyolv.don.mart.api.model.test;

import com.kattyolv.don.mart.api.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {

		System.out.println("EMPLOYEE");
		
		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("john");
		employee.setEmail("john@gmail.com");
		employee.setPassword("877");
		
		if(employee.getId() == 2 && 
				employee.getName() == "john" &&
				employee.getEmail() == "john@gmail.com" &&
				employee.getPassword() == "877") {
			
			System.out.println("All nice.");
		}
		else {
			System.out.println("Something went wrong.");
		}
	}

}
