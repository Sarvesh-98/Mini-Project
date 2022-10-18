package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Cart implements AutoCloseable {
	static int eQuantity;
	static int finalTotal;
	static int quantity;
	static int ProductID;
	static HashSet<Integer> hashSet = new HashSet<Integer>();
	 
	 
	static void addToCart() throws Exception {
		Scanner s = new Scanner(System.in);

		try {
			for(;;) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
				PreparedStatement ps = conn.prepareStatement("insert into Cart(UserId, ProductID, ProductName, ProductQuantity, ProductPrice,TotalPrice)values (?,?,?,?,?,?)");

			System.out.println("Enter ProductId to enter into cart or press 99 for checkout");
			ProductID = s.nextInt();
			
			if (ProductID>0 && ProductID<11 ) {
			System.out.println("Enter quantity ");
			quantity = s.nextInt();
			HashMap<Integer, String> name= Product.getProductName();
			HashMap<Integer, Integer> price = Product.getProductPrice();
			
			int itemPrice= price.get(ProductID);
			int total = quantity*itemPrice;
			finalTotal = finalTotal+ total;
			
			ps.setInt(1, HomePage.id);
			ps.setInt(2, ProductID);
			ps.setString(3, name.get(ProductID));
			ps.setInt(4, quantity);
			ps.setInt(5, price.get(ProductID));
			ps.setInt(6, total);
			ps.executeUpdate();
			

			
			ps = conn.prepareStatement("select OrderNumber from cart where UserId = ? and ProductId =?");
			ps.setInt(1, HomePage.id);
			ps.setInt(2, ProductID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int order =rs.getInt(1);
				hashSet.add(order);
			}
			
			updateQuantity();
			
			}else if (ProductID > 11 && ProductID < 99 || ProductID <0 || ProductID > 99 ) {
				System.out.println("Invalid input");
			}else if (ProductID == 99) {
				System.out.println("*********************************************************");
				viewCart();
			}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			s.close();
		}
			
	}
	static void updateQuantity() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
			PreparedStatement ps = conn.prepareStatement("select Quantity from Product");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				eQuantity = rs.getInt(1);
			}
			
			eQuantity = eQuantity - quantity;
			ps = conn.prepareStatement("update Product set Quantity = ? where ProductId =?");
			ps.setInt(1, eQuantity);
			ps.setInt(2, ProductID);
			ps.execute();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	static void viewCart(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
			PreparedStatement ps= conn.prepareStatement("select ProductName, ProductQuantity, ProductPrice, TotalPrice from cart where OrderNumber =?");
			System.out.println("*********************************************************");
			System.out.println("Product Name \t"+"Quantity \t" + "Price \t \t"+ "Total \t" );
			for(int i:hashSet) {
				ps.setInt(1, i);
				ResultSet rs =ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString(1) + "\t" + rs.getInt(2) + "\t \t" + rs.getInt(3) + "\t \t" + rs.getInt(4));
				}
			}
			System.out.println("Total Amount is : "+ finalTotal);
			
			System.out.println("*********************************************************");
			System.out.println(" Thanks for Shopping");
			System.out.println("*********************************************************");
			System.exit(1);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	 }
	@Override
	public void close() throws Exception {
	}
}
