package com.kattyolv.don.mart.api.jdbc.test;

import java.sql.Connection;

import com.kattyolv.don.mart.api.jdbc.ConnectionJDBC;

public class ConnectionJDBCTest {

	public static void main(String[] args) {
		
		ConnectionJDBC connectionJDBC = new ConnectionJDBC();
		Connection connected = connectionJDBC.getConnection();

		if(connected != null) {
			System.out.print("CONNECTED");
		}
		else {
			System.out.print("NO CONNECTION");
		}
	}

}
