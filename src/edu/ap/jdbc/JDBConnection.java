package edu.ap.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBConnection {
	
	//Singleton
	
	private static JDBConnection instance = null;
	private Connection conn = null;
	
	private JDBConnection() {}
	
	public static synchronized JDBConnection getJDBConnection() {
		
		if(instance == null) {
            instance = new JDBConnection();
        }
        return instance;
	}
	
	public void openConnection(String database, String user, String pwd) {        
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    String url = "jdbc:mysql://127.0.0.1/" + database;
		    conn = DriverManager.getConnection (url, user, pwd);
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void closeConnection() {
		
		if (conn != null) {
			try {
				conn.close();
		     }
		     catch (SQLException ex) {
		    	 System.out.println("Error: " + ex);
		     }
		}
	}
public List<Race> selectAll() {
	
		ResultSet rs = null;
		List<Race> result = new ArrayList<Race>();
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM RACES ORDER BY NAME ASC");
			while(rs.next()) {
				result.add(new Race(rs.getString("name"), rs.getInt("Distance")));
			}
			stmt.close();
		}
		catch(SQLException ex) {
			System.out.println("Error: " + ex);
		}
		
		return result;
	}

	
	

}
