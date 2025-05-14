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
}
