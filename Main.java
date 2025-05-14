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
