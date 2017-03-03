package ie.lyit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResSearch {


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
		    
		    ResultSet resultSet = stmt.executeQuery("select * from Restaurant");
		    
		    while(resultSet.next())
		    {
		    		int cName = resultSet.getInt("id");
		    		String cName2 = resultSet.getString("Name");
		    		String cName3 = resultSet.getString("Cuisine");
		    		int cName4 = resultSet.getInt("Price");
		    		System.out.println(cName+"\t"+cName2+"\t"+cName3+"\t"+cName4);
		    		
		    }
		    
		  
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