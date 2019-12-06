/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author kebson
 */
import java.sql.*;

// This class can be used to initialize the database connection 
public class DatabaseConnection { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		// Initialize all the information regarding 
		// Database Connection 
		String dbDriver = "com.mysql.jdbc.Driver"; 
		String dbURL = "jdbc:mysql://moustaphakebe.me:3306/"; 
		// Database name to access 
		String dbName = "bibliotheque"; 
		String dbUsername = "mouss"; 
		String dbPassword = "ToshibaGit0@"; 

		Class.forName(dbDriver); 
		Connection con=DriverManager.getConnection("jdbc:mysql://moustaphakebe.me:3306/bibliotheque","quentin","azertyuiop"); 
                System.out.println("con: "+con);
                PreparedStatement st = con 
                   .prepareStatement("select * from BOOKS");
            ResultSet rs =  st.executeQuery();
            while(rs.next()){
                System.out.println("hey: "+ rs.getString(2));
            }
            System.out.println("finnn:");
                
		return con; 
	} 
} 

