package com.kattyolv.don.mart.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kattyolv.don.mart.api.dao.DAOEmployee;
import com.kattyolv.don.mart.api.model.Employee;


@WebServlet("/employee/sign-in")
public class EmployeeSignInController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(email != null &&
					password != null) {
				
				String emailReplaced = email.replaceAll("\\s+", "");
				String passwordReplaced = password.replaceAll("\\s+", "");
				
				if(!emailReplaced.equals("") &&
						!passwordReplaced.equals("")) {
					
					DAOEmployee employeeDAO = new DAOEmployee();
					
					Employee employee = employeeDAO.selectEmployeeByEmailAndPassword(email, password);
					
					if(employee != null) {
						
						Gson gson = new Gson();
						String employeeJson = gson.toJson(employee);
						
						PrintWriter out = response.getWriter();
						out.println(employeeJson);
						
						response.setStatus(200);
						response.setContentType("application/json");
						
						return;
						
					}
					else {
						response.setStatus(401);
						return;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			
			return;
		}
		
		response.setStatus(400);
		
	}
}
