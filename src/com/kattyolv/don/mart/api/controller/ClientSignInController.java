package com.kattyolv.don.mart.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kattyolv.don.mart.api.dao.DAOClient;
import com.kattyolv.don.mart.api.model.Client;


@WebServlet("/client/sign-in")
public class ClientSignInController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if(email != null &&
					password != null) {
				
				String emailReplaced = email.replaceAll("//s+", "");
				String passwordReplaced = password.replaceAll("//s+", "");
				
				if(!emailReplaced.equals("") &&
						!passwordReplaced.equals("")) {
					
					DAOClient clientDAO = new DAOClient();
					
					Client client = clientDAO.selectClientByEmailAndPassword(email, password);
					
					if(client != null) {
						
						Gson gson = new Gson();
						String clientJson = gson.toJson(client);
						
						PrintWriter out = response.getWriter();
						out.println(clientJson);
						
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
