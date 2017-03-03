package ie.lyit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCreateDatabase{

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
		    String createDatabase = "CREATE DATABASE IF NOT EXISTS student"; 
		    stmt.executeUpdate(createDatabase);
		    System.out.println("STEP 4(a) COMPLETE - Query executed and database created...");

		    // STEP 4(b) - Execute Query (Create a Table)
		    String createTable = "CREATE TABLE IF NOT EXISTS students " +
	                   "(id INTEGER not NULL, " +
	                   " first VARCHAR(255), " + 
	                   " last VARCHAR(255), " + 
	                   " age INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 

		    stmt.executeUpdate("USE student");
		    stmt.executeUpdate(createTable);
		    System.out.println("STEP 4(b) COMPLETE - Query executed and table added to database...");

		    // STEP 4(c) - Execute Query (Insert data into students table)
		    String student1 = "INSERT INTO students VALUES (100001, 'Bart', 'Simpson', 15) "
		    		     + "ON DUPLICATE KEY UPDATE first='Bart', last='Simpson', age=15";
	        stmt.executeUpdate(student1);
		    String student2 = "INSERT INTO students VALUES (100002, 'Lisa', 'Simpson', 18) "
	    		     + "ON DUPLICATE KEY UPDATE first='Lisa', last='Simpson', age=18";
		    stmt.executeUpdate(student2);
		    String student3 = "INSERT INTO students VALUES (100003, 'Homer', 'Simpson', 47) "
	    		     + "ON DUPLICATE KEY UPDATE first='Homer', last='Simpson', age=47";
		    stmt.executeUpdate(student3);
		    String student4 = "INSERT INTO students VALUES (100004, 'Marge', 'Simpson', 46) "
	    		     + "ON DUPLICATE KEY UPDATE first='Marge', last='Simpson', age=46";
		    stmt.executeUpdate(student4);
	        System.out.println("STEP 4(c) COMPLETE - Query executed and data added to table..."); 
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