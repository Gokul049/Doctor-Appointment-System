# Doctor-Appointment-System
The Doctor Appointment System in Core Java enables adding/viewing doctors and patients, booking, viewing, and cancelling appointments. It uses object-oriented principles and in-memory data structures to efficiently manage healthcare scheduling.
# Java Program 
# main Class

package HospitalManagement;

import java.sql.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		try (Connection conn = DataBaseService.getConnection()){
			Patients p = new Patients(conn);
			Doctors d = new Doctors(conn);
			BookAppointment appointment = new BookAppointment(conn,p,d);
			while(true)
			{
				System.out.println("+---------------------------------+");
				System.out.println("|   Doctors Appointment System    |");
				System.out.println("+---------------------------------+");
				System.out.println("|       1. Add Patients           |");
				System.out.println("|       2. Add Doctors            |");
				System.out.println("|       3. View Patients          |");
				System.out.println("|       4. View Doctors           |");
				System.out.println("|       5. Book Appointment       |");
				System.out.println("|       6. View Appointments      |");
				System.out.println("|       7. Cancel Appointment     |");
				System.out.println("|       8. Exit                   |");
				System.out.println("+---------------------------------+");
				
				System.out.print("Enter Yours Choice: ");
				int ch = scan.nextInt();
				
				switch (ch)
				{
				case 1:
					p.addPatients();
					break;
				case 2:
					d.addDoctors();
					break;
				case 3:
					p.viewPatients();
					break;
				case 4:
					d.viewDoctors();
					break;
				case 5:
					appointment.bookAppointment();
					break;
				case 6:
					appointment.viewAppointment();
					break;
				case 7:
					appointment.cancelAppointment();
					break;
				case 8:
					System.out.println("Thank You🙏🙏🙏");
					System.exit(0);
				default:
					System.out.println("Please Enter Valid Choice....!");
					System.out.println("____________________________________________________________________________");
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
# Patients Class

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
# Doctors Class
'''python
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
'''
# DatabaseConnection Class
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
# Appointment Booking Class 
''' python
package HospitalManagement;

import java.sql.*;

import java.util.Scanner;

public class BookAppointment {
	private Connection conn;
	private Patients patients;
	private Doctors doctors;
	
	Scanner scan = new Scanner(System.in);
	
	public BookAppointment(Connection conn, Patients patients, Doctors doctors)
	{
		this.conn = conn;
		this.patients = patients;
		this.doctors = doctors;
	}
	
	public void bookAppointment() throws SQLException
	{
		System.out.print("Enter Patient's  Id: ");
		int patient_id = scan.nextInt();
		System.out.print("Enter Doctor's Id: ");
		int doctor_id = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter Appointment Date yyyy-mm-dd: ");
		String appointmentDate = scan.nextLine();
		
		if(!patients.getPatientsById(patient_id))
		{
			System.out.println("Please Provid Valid Patient's Id");
			return;
		}
		
		if(!doctors.getDoctorsById(doctor_id))
		{
			System.out.println("Please Provid Valid Doctors's Id");
			return;
		}	
		
		if(!checkAvailability(conn,doctor_id,appointmentDate))
		{
			System.out.println("Doctor Not Available.");
			return;
		}
		
		String query = "insert into appointments(patient_id, doctor_id, appointment_date) values(?, ?, ?)";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
				ps.setInt(1, patient_id);	
				ps.setInt(2, doctor_id);
				ps.setString(3, appointmentDate);
				if(ps.executeUpdate() > 0)
				{
					System.out.println("Appointment Booked Successfully🤩🤩🤩");
					System.out.println("____________________________________________________________________________");
				}
				else
				{
					System.out.println("Appointment not Booked🥹🥹🥹");
					System.out.println("____________________________________________________________________________");
				}
		}
		
		
	}
	
	public boolean checkAvailability(Connection conn, int doctor_id, String appointmentDate) throws SQLException
	{
		String query = "select count(1) from appointments where doctor_id = ? AND appointment_date = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setInt(1, doctor_id);
			ps.setString(2, appointmentDate);
			try(ResultSet rs = ps.executeQuery())
			{
				if(rs.next())
				{
					return rs.getInt(1) == 0;
				}
			}
		}
		return false;
	}
	
	public void viewAppointment() throws SQLException
	{
		String query = "select * from appointments";
		
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ResultSet rs = ps.executeQuery();
			System.out.println("+------------------------------------------------------+");
			System.out.println("|                 Booked Appointment                   +");
			System.out.println("+--------+--------------+--------------+---------------+");
			System.out.println("|S.No    |patient_id    |Doctor_id     |AppointmentDate|");
			System.out.println("+--------+--------------+--------------+---------------+");
			while(rs.next())
			{
				int id = rs.getInt(1);
				int p_id = rs.getInt(2);
				int d_id = rs.getInt(3);
				String date = rs.getString(4);
				
				System.out.printf("|%-8s|%-14s|%-14s|%-15s|\n",id,p_id,d_id,date);
				System.out.println("+--------+--------------+--------------+---------------+");
			}
			System.out.println("____________________________________________________________________________");
		}
	}

	public void cancelAppointment() throws SQLException 
	{
		System.out.println("======Appointment Cancelation =====");
		System.out.print("Enter Patient's Id: ");
		int p_id = scan.nextInt();
		System.out.print("Enter Doctors's Id: ");
		int d_id = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter Appointment Date(yyyy-mm-dd): ");
		String date = scan.nextLine();	
		
		String query = "delete from appointments where patient_id = ? AND doctor_id = ? AND appointment_date = ?";
		try(PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setInt(1, p_id);
			ps.setInt(2, d_id);
			ps.setString(3, date);
			
			if(ps.executeUpdate() > 0)
			{
				System.out.println("Appointment Canceled Successfully......!!");
				System.out.println("____________________________________________________________________________");
			}
			else
			{
				System.out.println("Check Patient's Id or Doctor's Id or AppointmentDate ");
				System.out.println("____________________________________________________________________________");
			}
	}
		
	}
}'''
# SQL 
Doctor Table
CREATE TABLE doctors (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255) NOT NULL,
  department VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

Patients Table
CREATE TABLE patients (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  age VARCHAR(3) NOT NULL,
  gender VARCHAR(7) NOT NULL,
  PRIMARY KEY (id)  
);
Appointments Table
CREATE TABLE appointments (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  patient_id MEDIUMINT(8) NOT NULL,
  doctor_id MEDIUMINT(8) NOT NULL,
  appointment_date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES patients (id),
  FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);

# Output
![image](https://github.com/user-attachments/assets/55ed719e-b33e-4859-95cd-7922e6fcdd21)



