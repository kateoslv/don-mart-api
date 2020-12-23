package com.kattyolv.don.mart.api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kattyolv.don.mart.api.dao.DAOEmployee;
import com.kattyolv.don.mart.api.model.Employee;


@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			DAOEmployee employeeDAO = new DAOEmployee();
			ArrayList<Employee> employees = employeeDAO.selectEmployee();
			
			Gson gson = new Gson();
			String employeesJson = gson.toJson(employees);
			
			PrintWriter out = response.getWriter();
			out.print(employeesJson);
			
			response.setContentType("application/json");
			response.setStatus(200);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			DAOEmployee employeeDAO = new DAOEmployee();
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if(name != "" &&
					address != "" &&
					email != "" &&
					password != "") {
				
				Employee employee = new Employee();
				
				employee.setName(name);
				employee.setAddress(address);
				employee.setEmail(email);
				employee.setPassword(password);
				
				boolean wasInserted = employeeDAO.insertEmployee(employee);
				
				if(wasInserted == true) {
					response.setStatus(200);
				}
			}
			else {
				response.setStatus(400);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

}
