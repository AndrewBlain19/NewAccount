package ie.lyit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddRestaurant {




	public static void main(String[] args) {
		// JDBC Driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/teamproject?autoReconnect=true&useSSL=false";
		final String USER_NAME = "root";
		final String PASSWORD = "password";

		Scanner keyboardIn = new Scanner (System.in);
		Connection conn=null;
		
		try{
			// STEP 1 - Load the JDBC driver
			//          Initialize a driver to open a communications channel with the database.
			Class.forName(JDBC_DRIVER);
			System.out.println("STEP 1 COMPLETE - Driver Registered...");
			
			// STEP 2 - Open a connection
			//          Use the DriverManager.getConnection() method to create a Connection object,
			//          which represents a physical connection with the database server.
		    conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		    System.out.println("STEP 2 COMPLETE - Connection obtained...");
		    
		    // STEP 3 - Create Statement object		    
		    Statement stmt = conn.createStatement();
		    System.out.println("STEP 3 COMPLETE - Statement object created...");
		    
		    stmt.executeUpdate("USE Restaurant");
		    
		    
		    
		    String Name, Cuisine;
		    int id, Price;
		    System.out.print("Restaurant id: ");
		    id = keyboardIn.nextInt();
		    System.out.print("Add Restaurant Name: ");
		    Name = keyboardIn.next();
		    System.out.print("Cuisine Type: ");
		    Cuisine = keyboardIn.next();
		    System.out.print("Restaurant Price: ");
		    Price = keyboardIn.nextInt();
		    
		    

		    // STEP 4(c) - Execute Query (Insert data into students table)
		    String restaurant3 = "INSERT INTO Restaurant (id, Name, Cuisine, Price) VALUES ('"+id+"', '"+Name+"','"+ Cuisine+"', '"+Price+"')"
		    		     + "ON DUPLICATE KEY UPDATE Name=Name, Cuisine=Cuisine, Price=Price";
	        stmt.executeUpdate(restaurant3);
		  
		}
		catch (ClassNotFoundException e){
			System.out.println("Could not load driver.\n" + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("Problem with SQL.\n" + e.getMessage());
		}
		finally{
			try{
				conn.close();
			    System.out.println("STEP 6 COMPLETE - Connection closed.");				
			}
			catch (SQLException e){
				System.out.println("Could not close connection.\n" + e.getMessage());
			}
		}
	}
}