package com.kattyolv.don.mart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DAOEmployee employeeDAO = new DAOEmployee();
			
			Employee employee = new Employee();
			
			InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String bodyRequest = bufferedReader.readLine();
			
			String[] bodyRequestSplitted = bodyRequest.split("&");
			
			for(String infoBodyRequest : bodyRequestSplitted) {
				
				String[] infoBodyRequestSplitted = infoBodyRequest.split("=");
				
				try {
					String key = infoBodyRequestSplitted[0];
					String value = infoBodyRequestSplitted[1];
					
					String decodedValue = URLDecoder.decode(value, "UTF-8");
						
					switch(key) {
						case "name":
							employee.setName(decodedValue);
							break;
						case "address":
							employee.setAddress(decodedValue);
							break;
						case "email":
							employee.setEmail(decodedValue);
							break;
						case "password":
							employee.setPassword(decodedValue);
							break;
						default:
							response.getWriter().print("Invalid key.");
							break;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					response.setStatus(400);
					return;
				}
			}
			
			boolean wasUpdated = employeeDAO.updateEmployee(employee);
			
			if(wasUpdated == true) {
				response.setStatus(200);
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

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			DAOEmployee employeeDAO = new DAOEmployee();
			
			InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String bodyRequest = bufferedReader.readLine();
			
			if(bodyRequest != null) {
				
				String[] bodyRequestSplitted = bodyRequest.split("=");
				
				String key = bodyRequestSplitted[0];
				
				if(bodyRequestSplitted.length > 1 &&
						key.equals("id")) {
					
					String value = bodyRequestSplitted[1];
						
					int id = Integer.parseInt(value);
				
					boolean hasDeleted = employeeDAO.deleteEmployee(id);
					
					if(hasDeleted == true) {
						response.setStatus(200);
						return;
					}
				}
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
		
		response.setStatus(400);
		
	}
	
}
