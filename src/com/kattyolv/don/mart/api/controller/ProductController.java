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

}
