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
import com.kattyolv.don.mart.api.dao.DAOClient;
import com.kattyolv.don.mart.api.model.Client;


@WebServlet("/client")
public class ClientController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DAOClient clientDAO = new DAOClient();
			ArrayList<Client> clients = clientDAO.selectClient();
			
			Gson gson = new Gson();
			String clientsJson = gson.toJson(clients);
			
			PrintWriter out = response.getWriter();
			out.print(clientsJson);
			
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
			
			DAOClient clientDAO = new DAOClient();
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Client client = new Client();
			client.setName(name);
			client.setAddress(address);
			client.setEmail(email);
			client.setPassword(password);
			
			boolean wasInserted = clientDAO.insertClient(client);
			
			if(wasInserted == true) {
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

}
