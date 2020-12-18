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

}
