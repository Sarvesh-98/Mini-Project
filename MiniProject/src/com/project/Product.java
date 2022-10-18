package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

public class Product implements AutoCloseable {	
	static Scanner s = new Scanner(System.in);
	static HashMap<Integer, String> name = new HashMap<Integer, String>();
	static HashMap<Integer, Integer> price = new HashMap<Integer, Integer>();	 
	 static {
		 System.out.println("Welcome to The Groccery's Shop");
		 System.out.println("------------------------------------------");
	 }
	
	 static void product() throws Exception {
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
			PreparedStatement ps=null;
			
						
			for(;;) {
				System.out.println("Enter 1 to sort by id or 2 to sort by name or 3 to sort by price");
				int sort = s.nextInt();
				if(sort == 1) {
					ps = conn.prepareStatement("select * from Product order by ProductID asc");
					
					break;
				}else if (sort == 2) {
					ps = conn.prepareStatement("select * from Product order by ProductName asc");
					break;
				}else if(sort == 3) {
					ps = conn.prepareStatement("select * from Product order by ProductPrice asc");
					break;
				}
				else {
					System.out.println("Invalid input");
				}
				
			}
			System.out.println("----------------------------------");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Id- " + rs.getInt(1) + "\t" + rs.getString(2) +"\t" + rs.getString(3)+ "\t" + "Price-" + "\t" + rs.getInt(5) );
				
			}
			
			Cart.addToCart();
		}catch (Exception e) {
			System.out.println(e.getMessage());			
		}
		
	}
			static public HashMap<Integer, String> getProductName() throws Exception{
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
					PreparedStatement ps= conn.prepareStatement("select ProductID,ProductName from Product");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						int id =rs.getInt(1);
						String ProductName = rs.getString(2);
						 name.put(id, ProductName);
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return name;
			}
			
			static public HashMap<Integer, Integer> getProductPrice() throws Exception{
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
					PreparedStatement ps= conn.prepareStatement("select ProductID,Price from Product");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						int id =rs.getInt(1);
						int Price = rs.getInt(2);
						 price.put(id, Price);
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return price;
			}
			
			@Override
			public void close() throws Exception {
			}

}

	
	
     