package ie.lyit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Restaurant {


	public static void main(String[] args) {
		// JDBC Driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/teamproject?autoReconnect=true&useSSL=false";
		final String USER_NAME = "root";
		final String PASSWORD = "password";

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

		    // STEP 4(a) - Execute Query (Create a Database)
		    String createDatabase = "CREATE DATABASE IF NOT EXISTS Restaurant"; 
		    stmt.executeUpdate(createDatabase);
		    System.out.println("STEP 4(a) COMPLETE - Query executed and database created...");

		    // STEP 4(b) - Execute Query (Create a Table)
		    String createTable = "CREATE TABLE IF NOT EXISTS Restaurant " +
	                   "(id INTEGER not NULL, " +
	                   " Name VARCHAR(255), " + 
	                   " Cuisine VARCHAR(255), " + 
	                   " Price INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 

		    stmt.executeUpdate("USE Restaurant");
		    stmt.executeUpdate(createTable);
		    System.out.println("STEP 4(b) COMPLETE - Query executed and table added to database...");

		    // STEP 4(c) - Execute Query (Insert data into students table)
		    String restaurant1 = "INSERT INTO Restaurant VALUES (100001, 'The Yellow Pepper', 'Traditional', 25) "
		    		     + "ON DUPLICATE KEY UPDATE Name='The Yellow Pepper', Cuisine='Traditional', Price=25";
	        stmt.executeUpdate(restaurant1);
	        String restaurant2 = "INSERT INTO Restaurant VALUES (100002, 'Oasis', 'Traditional', 30) "
	    		     + "ON DUPLICATE KEY UPDATE Name='Oasis', Cuisine='Traditiona;', Price=30";
		    stmt.executeUpdate(restaurant2);
		  
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