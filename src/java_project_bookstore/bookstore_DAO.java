package java_project_bookstore;

import java.sql.*;

public class bookstore_DAO {
	private Connection con;
	private Statement smt;
	
	public bookstore_DAO() {
		String url = "jdbc:mysql://192.168.1.34:3306/kt_project_bookstore";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(url, "root", "sj4321");
			
			if(conn!=null) {
				System.out.println("Connecting successful");
			}
			else {
				System.out.println("Connecting failed");
			}
			smt = conn.createStatement();
			System.out.println("Statement created!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
