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
			response.setStatus(500);
		}
	}

}
