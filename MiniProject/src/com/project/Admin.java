package com.project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class Admin {

	static Scanner s = new Scanner(System.in);
	
	public static void chooseOptions() {
		System.out.println("Hello, Admin");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Press 1 to check the Quantity of product "); 
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Press 2 to get registered user list ") ; 
		System.out.println("----------------------------------------------------------------------------");
	    System.out.println("press 3 to get user history");
	    System.out.println("----------------------------------------------------------------------------");
	    int select = s.nextInt();
		switch (select) {
	case 1:
		quantity();
		break;
	case 2:
		regUserList();
		break;
	case 3:
		userHistory();
		break;
	case 4:
		System.exit(2);
	default:
		System.out.println("invalid selection");
	}
	}
	
	public static void quantity() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
			PreparedStatement ps = conn.prepareStatement("select ProductID, ProductName, Quantity from Product");
			ResultSet rs=  ps.executeQuery();
			while(rs.next()) {
				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Id " + rs.getInt(1) + " Name " + rs.getString(2)+ " Quantity "+ rs.getInt(3) );
				System.out.println("----------------------------------------------------------------------------");
			}
			chooseOptions();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}

	public static void regUserList()  {
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
			PreparedStatement ps=conn.prepareStatement("Select id, LastName, FirstName, EmailId, UserName, Address, Contact from Userinfo");
			ResultSet rs = ps.executeQuery();
            System.out.println("Registered User Details are : ");
            
            	while(rs.next()) {
            		System.out.println("----------------------------------------------------------------------------");
    				System.out.println("Id "+ rs.getInt(1));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("LastName "+ rs.getString(2));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("FirstName "+ rs.getString(3));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("UserName "+ rs.getString(4));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("EmailId "+ rs.getString(5));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("Address "+ rs.getString(6));
    				System.out.println("----------------------------------------------------------------------------");
    				System.out.println("");
    			}
	        chooseOptions();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
		 
		public static void userHistory() {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
					PreparedStatement ps = conn.prepareStatement("select id, ProductID, ProductName, ProductQuantity, ProductPrice, TotalPrice from Cart ");
					System.out.println("Enter the User Id to get order history for user");
					int num = s.nextInt();
					ps.setInt(1, num);
					ResultSet rs = ps.executeQuery();
					System.out.println("userId \t" + "ProductId \t" + "productName \t" + "Quantity \t" + "price \t" + "Total \t");
					while(rs.next()) {
						System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t\t" + rs.getInt(5) + "\t" + rs.getInt(6));
					}
					chooseOptions();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
	public static void main(String[]args) {
		Admin.chooseOptions();
	}
}	
	
		
	