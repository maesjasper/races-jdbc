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
	
	
public ArrayList<String> selectAll() {
		
		ResultSet rs = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM RACES ORDER BY NAME DESC");
			while(rs.next()) {
				result.add(rs.getString("name") + ";" + rs.getInt("distance"));
			}
			stmt.close();
		}
		catch(SQLException ex) {
			System.out.println("Error: " + ex);
		}
		
		return result;
	}
	
public void executeInsert(String table, String name, int distance) {
	
	try {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO " + table + "(name, distance) VALUES('" + name + "'," + distance + ")");
		//stmt.executeUpdate("INSERT INTO " + table + " VALUES('" + name + "'," + distance + ")");
		stmt.close();
	}
	catch(SQLException ex) {
		System.out.println("Error: " + ex);
	}
}
	

}
