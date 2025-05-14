package HospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
	
	private Connection conn;

	Scanner scan = new Scanner(System.in);
	
	public Doctors(Connection conn)
	{
		this.conn = conn;
	}
	
	public void addDoctors() throws SQLException
	{
		System.out.print("Enter Name: ");
		String name = scan.nextLine();
		
		System.out.print("Enter Department: ");
		String department = scan.nextLine();
		
		String query = "insert into doctors (name, department) values(?, ?)";
		
		try(PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, name);
			
			ps.setString(2, department);
			
			if(ps.executeUpdate() > 0)
			{
				System.out.println("Doctors Added Successfully🤩🤩🤩");
				System.out.println("____________________________________________________________________________");
			}
			else
			{
				System.out.println("Failed to add Patients🥹🥹🥹");
				System.out.println("____________________________________________________________________________");
			}
			
		}
		
		
	}
	
	public void viewDoctors() throws SQLException
	{
		String query = "select * from doctors";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ResultSet rs = ps.executeQuery();
			System.out.println("+------------------------------------------------");
			System.out.println("|                  DOCTORS TABLE                |");
			System.out.println("+------+-----------------+----------------------+");
			System.out.println("|  Id  |      Name       |       Department     |");
			System.out.println("+------+-----------------+----------------------+");
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String  department = rs.getString("department");
				System.out.printf("|%-6s|%-17s|%-22s|",id,name,department);
				System.out.println();
				//System.out.println("|'"+id+"'      |'"+name+"'                 |'"+department+"'                      |");
				System.out.println("+------+-----------------+----------------------+");
			}
			System.out.println("____________________________________________________________________________");
		} 
		
				
	}
	
	public boolean getDoctorsById(int id) throws SQLException
	{
		String query = "select count(1) from doctors where id = ? ";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery())
			{
				if(rs.next())
				{
					return rs.getInt(1) > 0;
				}
			}
			
			
		}
		return false; 
		
	}

}
