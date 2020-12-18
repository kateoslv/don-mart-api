package com.kattyolv.don.mart.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kattyolv.don.mart.api.dao.DAOProduct;
import com.kattyolv.don.mart.api.model.Product;


@WebServlet("/product")
public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DAOProduct productDAO = new DAOProduct();
			ArrayList<Product> products = productDAO.selectProduct();
			
			Gson gson = new Gson();
			String productsJson = gson.toJson(products);
			
			PrintWriter out = response.getWriter();
			out.println(productsJson);
			
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
			
			DAOProduct productDAO = new DAOProduct();
			
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			Double convertedPrice = Double.parseDouble(price);
			
			Product product = new Product();
			product.setName(name);
			product.setPrice(convertedPrice);
			
			boolean wasInserted = productDAO.insertProduct(product);
			
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

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			DAOProduct productDAO = new DAOProduct();
			
			Product product = new Product();
			
			InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String bodyRequest = bufferedReader.readLine();
			
			String[] bodyRequestSplitted = bodyRequest.split("&");
			
			for (String infoBodyRequest : bodyRequestSplitted) {
				
				String[] infoBodyRequestSplitted = infoBodyRequest.split("=");
				
				String key = infoBodyRequestSplitted[0];
				String value = infoBodyRequestSplitted[1];
				
				if(key.equalsIgnoreCase("id")) {
					int convertedId	= Integer.parseInt(value);
					product.setId(convertedId);
				}
				if(key.equalsIgnoreCase("name")) {
					product.setName(value);
				}
				if(key.equalsIgnoreCase("price")) {
					Double convertedValue = Double.parseDouble(value); 
					product.setPrice(convertedValue);
				}
			}
			
			boolean wasUpdated = productDAO.updateProduct(product);
			
			System.out.println(wasUpdated);
			
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
			
			DAOProduct productDAO = new DAOProduct();
			
			InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String bodyRequest = bufferedReader.readLine();
			
			final String ID_REQUEST_KEY = "id=";
			
			if(bodyRequest == null || 
					bodyRequest.equalsIgnoreCase(ID_REQUEST_KEY) ||
					!bodyRequest.contains(ID_REQUEST_KEY)) {
				
				response.getWriter().print("id is required.");
				response.setStatus(400);
				return;
			}
			
			String idValue = bodyRequest.replace(ID_REQUEST_KEY, "");
			
			int convertedId = Integer.parseInt(idValue);
			
			boolean hasDeleted = productDAO.deleteProduct(convertedId);
			
			if(hasDeleted == true) {
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
