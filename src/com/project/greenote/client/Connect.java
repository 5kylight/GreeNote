package com.project.greenote.client;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class Connect {
	
	private static Connection connection = null;
	private static Statement statement = null;
//	private static ResultSet resultSet = null;
	
	public Connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/root", "root",
					"");
			statement = (Statement) connection.createStatement();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
			
		

	}
		public boolean login(String login, String passwd){
			String query = "SELECT password FROM Users WHERE login=\""+login+"\" ";
			try {
				ResultSet getted = statement.executeQuery(query);
				if(getted.getString("password")==passwd)return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
			
		}
		
	
	
//	public static void main(String[] args){
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = (Connection) DriverManager.getConnection(
//					"jdbc:mysql://localhost/notes", "root",
//					"");
//			statement = (Statement) connection.createStatement();
//		} catch (ClassNotFoundException | SQLException e1) {
//			e1.printStackTrace();
//		}
//		String query = "select * from Bookmarks";
//		try {
//			resultSet = statement.executeQuery(query);
//
//			while (resultSet.next()) {
//				String id = resultSet.getString("ID");
//				String url = resultSet.getString("URL");
//				String category = resultSet.getString("Category");
//				String description = resultSet.getString("Description");
//				Date data = resultSet.getDate("Time");
//				Time time = resultSet.getTime("Time");
//				System.out.println("Category: " + category);
//				System.out.println("ID: " + id);
//				System.out.println("Website: " + url);
//				System.out.println("Decription: " + description);
//				System.out.println("Date time: " + data + " " + time);
//				System.out.println("_________________________________");
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//			
//		
//	}

}
