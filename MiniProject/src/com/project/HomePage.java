package com.project;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public  class HomePage {
	static Scanner input = new Scanner(System.in);	
  String email;
  String password;
 String username;
String firstname;
String lastname;
String address;
  String contact;
  
  public static void chooseAccount() {
	  
	  
	  System.out.println("Please press Enter 0 to SignIn");
		System.out.println("Please press Enter 1 to UserLogIn ");
		System.out.println("Please press Enter 2 to AdminLogIn ");
		int start = input.nextInt();
					
			switch (start) {
			case 1:
				User();
				break;
			case 2:
				Admin();
				break;
			case 0:
				SignIn();
				break;
			default:
				System.out.println("Invalid input please try again");
			}
		}
  
  public static void SignIn() {
		  
	  try
      { 

   	   Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");

			PreparedStatement ps=conn.prepareStatement("insert into Userinfo(LastName,FirstName,EmailId,UserName,Password,Address,Contact) values(?,?,?,?,?,?,?)");  
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		  
			System.out.println("Enter Details to Register");
    	
			do {
			 
         System.out.println("last name : ");
         String lastname=br.readLine();  
         System.out.println("first name : ");
         String firstname=br.readLine();  
         System.out.print("email : "); 
      	String email=br.readLine(); 
         System.out.print("username : ");
         String username=br.readLine();  
         System.out.print("password : ");
         String password=br.readLine();  
          System.out.println("address : ");
         String address=br.readLine();  
         System.out.println("contact number : ");
         String contact=br.readLine();
         
         
         
           
         ps.setString(1,firstname);
         ps.setString(2,lastname);
         ps.setString(3,email);
         ps.setString(4,username);
         ps.setString(5,password);
         ps.setString(6,address);
         ps.setString(7,contact);
         int i=ps.executeUpdate();  
         System.out.println(i+" records affected");
         
         System.out.println("Do you want to Submit: y/n");  
         String s=br.readLine();  
         if(s.startsWith("y")){  
       	  System.out.println("Registered Successfull");
       	chooseAccount();
         break;  
         }  
         }while(true); 



            conn.close(); 

      } catch (Exception e) { 
          System.err.println("Got an exception! "); 
          System.err.println(e.getMessage()); 
        
      } 
      
  }

		
	  static int id;
  
  public static  void User() {
 

      	System.out.println("Enter Details to LogIN");
    	
      	try {
         	 Class.forName("com.mysql.cj.jdbc.Driver"); 
   			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
   						PreparedStatement ps=conn.prepareStatement("Select * from Userinfo where username = ? and password =?");
   			
   			BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
        
   		
             System.out.println("username : ");
         	String username=br.readLine();
         	System.out.print("password : ");
   		String password=br.readLine();
   		
   		ps.setString(1,username);
   		ps.setString(2,password);
   		
   		 ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               System.out.println("Logged In Successfully");
               Product.product();             
            }
            else {
           	 System.err.println("Incorrect username or password");
           	chooseAccount();
            }
           } catch (Exception e) { 
               System.err.println("Got an exception! "); 
               System.err.println(e.getMessage()); 
           }
  }
		 public static void Admin() {
			 
		      	System.out.print("Enter Details to LogIN");
		    	
		      	try {
		      	 Class.forName("com.mysql.cj.jdbc.Driver"); 
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "9764631771");
								PreparedStatement ps=conn.prepareStatement("Select * from Userinfo where UserName = ? and Password =?");
					
					BufferedReader br=new BufferedReader(new InputStreamReader(System.in));				
		          System.out.print("username : ");
		      	String username=br.readLine();
		      	System.out.print("Password : ");
				String password=br.readLine();
				
				ps.setString(1,username);
				ps.setString(2,password);
				
				 ResultSet rs = ps.executeQuery();
		         if (rs.next()) {
		            System.out.println("Logged In Successfully");
		            Admin.chooseOptions();
		         }
		         else {
		        	 System.err.println("Incorrect username or password");
		        	 chooseAccount();
		         }		          
		        } catch (Exception e) { 
		            System.err.println("Got an exception! "); 
		            System.err.println(e.getMessage()); 
		        } 		        
		    }	
  }
		


