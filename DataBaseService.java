package HospitalManagement;

import java.sql.*;

public class DataBaseService {
	private static Connection conn;
	
	public static Connection createConnection() throws SQLException, ClassNotFoundException
	{
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
//		System.out.println("Database Connection Created Successfully......!!");
		return conn;
	}
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException
	{
		if(conn==null)
			return createConnection();
		else
			return conn;
		
	}
	
}
