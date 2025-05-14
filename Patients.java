package HospitalManagement;

import java.sql.*;

import java.util.Scanner;

public class Patients {
	private Connection conn;

	Scanner scan = new Scanner(System.in);
	
	public Patients(Connection conn)
	{
		this.conn = conn;
	}
	
	public void addPatients() throws SQLException
	{
		System.out.print("Enter Name: ");
		String name = scan.nextLine();
		System.out.print("Enter Age: ");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter Gender: ");
		String gender = scan.nextLine();
		
		String query = "insert into patients (name, age, gender) values(?, ?, ?)";
		
		try(PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			
			if(ps.executeUpdate() > 0)
			{
				System.out.println("Patients Added Successfully🤩🤩🤩");
				System.out.println("____________________________________________________________________________");
			}
			else
			{
				System.out.println("Failed to add Patients🥹🥹🥹");
				System.out.println("____________________________________________________________________________");
			}
			
		}
		
		
	}
	
	public void viewPatients() throws SQLException
	{
		String query = "select * from patients";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ResultSet rs = ps.executeQuery();
			System.out.println("-------------------------------------------------");
			System.out.println("|                 PATIENTS TABLE                |");
			System.out.println("+------+-----------------+--------+-------------+");
			System.out.println("|  Id  |      Name       |   Age  |    Gender   |");
			System.out.println("+------+-----------------+--------+-------------+");
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String  gender = rs.getString("gender");
				System.out.printf("|%-6s|%-17s|%-8s|%-13s|",id,name,age,gender);
				System.out.println();
				//System.out.println("|'"+id+"'      |'"+name+"'                 |'"+age+"'        |'"+gender+"'             |");
				System.out.println("+------+-----------------+--------+-------------+");
			}
			System.out.println("____________________________________________________________________________");
		} 
		
				
	}
	
	public boolean getPatientsById(int id) throws SQLException
	{
		String query = "select count(1) from patients where id = ? ";
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
