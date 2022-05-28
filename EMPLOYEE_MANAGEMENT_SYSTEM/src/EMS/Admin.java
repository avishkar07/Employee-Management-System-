package EMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;

//-----------------Inheritance-------------------

public class Admin extends Employee {
	// fields
	public static String AdmName;
	public static String AdmAddress;
	public static long AdmContact;
	public static String AdmEmail;
	public static String AdmDesignation;
	public static int ID;
	public static String AdmDOJ;
	public static int AdmSalary;
	public static String Adminpassword;
	public static int Adminid;
	public static double salary1 = 0.0;

	public static void Adminmethod() throws SQLException, adminException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose :");
		System.out.println("1 for Admin login ");
		System.out.println("2 for Admin Sign up");
		int key = scanner.nextInt();
		Admin admin = new Admin();
		switch (key) {
		case 1: {
			System.out.println("-------Admin Login-------");
			try {
				adminlogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 2: {
			System.out.println("------Admin Sign Up--------");
			try {

				admin.adminsignup();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			System.out.println("-------Admin Login-------");

			try {
				adminlogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}

	public static void adminlogin() throws Exception {
		int status = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Admin Id:");
		ID = scanner.nextInt();
		System.out.println("Enter the Password");
		Adminpassword = scanner.next();

		Admin.RetrivAdmin();
		while (status == 0) {
			System.out.println("\n*********Welcome to the Employee Management System**********\n");
			System.out.println("1). Display all Employees working in this company\n" + "2). Search for Employee\n"
					+ "3). Edit Employee details\n" + "4). Delete Employee Details\n" + "5). Employee Salary\n"
					+ "6). Employee Attendce\n" + "7). EXIT\n");

			System.out.println("Enter your choice : ");
			int key = scanner.nextInt();

			switch (key) {
			case 1:
				System.out.println("All Employee Details :");
				profile();
				break;
			case 2:
				searchemployee();
				break;
			case 3:
				int j = 0;
				do {
					System.out.println("Enter the Employee ID to EDIT the details");
					int updateId = scanner.nextInt();
					int ch1 = 0;

					Connection connection = null;
					connection = ConnectionUtil.getConnection();

					Statement statement = connection.createStatement();
					System.out.println("\nEDIT Employee Details :\n" + "1). Employee ID\n" + "2). Name\n"
							+ "3). Salary\n" + "4). Contact No.\n" + "5). Email-ID\n" + "6). GO BACK\n");
					System.out.println("Enter your choice : ");
					ch1 = scanner.nextInt();

					switch (ch1) {
					case 1:
						System.out.println("\nEnter new Employee ID:");
						int newId = scanner.nextInt();
						String query = "UPDATE Employee " + "SET employeeid =" + newId + " WHERE employeeid ="
								+ updateId;
						statement.executeUpdate(query);
						System.out.println("Employee ID	Updated");
						System.out.println("____________________________________________");
						break;

					case 2:
						System.out.println("Enter new Employee Name:");
						String newname = scanner.next();
						String queryname = "UPDATE Employee " + "SET EmpName ='" + newname + "' WHERE employeeid ="
								+ updateId;
						statement.executeUpdate(queryname);
						System.out.println("Name Updated");
						System.out.println("____________________________________________");
						break;

					case 3:
						System.out.println("Enter new Employee Salary:");
						int salary = scanner.nextInt();
						String querysalary = "UPDATE Employee " + "SET Empsalary =" + salary + " WHERE employeeid ="
								+ updateId;
						statement.executeUpdate(querysalary);
						System.out.println("Salary	Updated");
						System.out.println("____________________________________________");
						break;

					case 4:
						System.out.println("Enter new Employee Contact No. :");
						long contact_no = scanner.nextLong();
						String querycontact = "UPDATE Employee " + "SET Empcontact =" + contact_no
								+ " WHERE employeeid =" + updateId;
						statement.executeUpdate(querycontact);
						System.out.println("Contact No	Updated");
						System.out.println("____________________________________________");
						break;

					case 5:
						System.out.println("Enter new Employee Email-ID :");
						String email_id = scanner.next();
						String queryemail = "UPDATE Employee " + "SET EmpEmail ='" + email_id + "' WHERE employeeid ="
								+ updateId;
						statement.executeUpdate(queryemail);
						System.out.println("E-Mail Updated");
						System.out.println("____________________________________________");
						break;

					case 6:
						j++;
						break;

					default:
						System.out.println("\nEnter a correct choice from the List :");
						System.out.println("____________________________________________");
						break;
					}
				} while (j == 0);
				break;

			case 4: {
				System.out.println("\nEnter Employee ID to DELETE from the Database :");
				int deleteid = scanner.nextInt();
				int k = 0;
				try {
					Connection connection = null;
					connection = ConnectionUtil.getConnection();

					Statement statement = connection.createStatement();
					String query = "DELETE FROM Employee WHERE employeeid = " + deleteid + ";";
					int delete = statement.executeUpdate(query);
					System.out.println("Record Deleted");
					System.out.println("____________________________________________");
					k++;
					if (k == 0) {
						System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
				break;
			}

			case 5:
				System.out.println("\n Enter Employee  Id to calculate salary : ");
				int calid = scanner.nextInt();
				Connection connection = null;
				connection = ConnectionUtil.getConnection();

				Statement statement = connection.createStatement();
				String query = "select empsalary from employee where employeeid =" + calid + ";";
				ResultSet resultSet = statement.executeQuery(query);

				while (resultSet.next()) {

					salary1 = resultSet.getInt(1);
					System.out.println("Gross salary :" + resultSet.getInt(1));

				}
				System.out.println("enter Income Tax %");
				double it = scanner.nextDouble();
				System.out.println("enter Professional Tax %");
				double pt = scanner.nextDouble();
				System.out.println("enter Provident Fund %");
				double pf = scanner.nextDouble();

				pf = pf * (salary1 / 100);
				it = it * (salary1 / 100);
				pt = pt * (salary1 / 100);
				double netSalary = salary1 - it - pt - pf;
				System.out.println("Net Salary is=" + netSalary);

				String query1 = "Insert into salary values(?,?,?,?,?,?);";
				PreparedStatement preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, calid);
				preparedStatement.setDouble(2, salary1);
				preparedStatement.setDouble(3, it);
				preparedStatement.setDouble(4, pt);
				preparedStatement.setDouble(5, pf);
				preparedStatement.setDouble(6, netSalary);
				int update = preparedStatement.executeUpdate();
				System.out.println("Salary Inserted " + update);
				break;
			case 6:
				Connection connection2 = null;
				connection = ConnectionUtil.getConnection();
				Statement statement2 = connection.createStatement();
				String query4 = "SELECT * FROM EMS.attendace;";

				ResultSet resultSet1 = statement2.executeQuery(query4);
				System.out.println("________________Attendace Table ________________");
				System.out.printf("%-15s%-15s%-15s%-15s\n", "ID", "Name", "Address", "Contact No", "Email",
						"Designation", "DOJ", "Salary", "Password");

				while (resultSet1.next()) {
					System.out.println("--------------------------------------------------------------------------");
					System.out.printf("%-15s%-15s%-15s%-15s\n", resultSet1.getInt(1), resultSet1.getString(2),
							resultSet1.getString(3), resultSet1.getString(4));
				}
				break;

			case 7:
				System.out.println("\nYou have chosen EXIT !!");
//				scanner.close();
//				System.exit(0);
				status++;
				System.out.println("_________________________________________________");
				break;
			}

		}
	}

	public void adminsignup() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID :");
		this.Adminid = scanner.nextInt();
		System.out.println(" Name :");
		AdmName = scanner.next();
		System.out.println("Address :");
		AdmAddress = scanner.next();
		System.out.println("Contact No :");
		AdmContact = scanner.nextLong();
		System.out.println("Email :");
		AdmEmail = scanner.next();
		System.out.println("Designation :");
		AdmDesignation = scanner.next();
		System.out.println("Date Of Join :");
		AdmDOJ = scanner.next();
		System.out.println("Salary :");
		AdmSalary = scanner.nextInt();
		System.out.println("Password :");
		Adminpassword = scanner.next();
		Admin admin = new Admin();
		admin.InsertAdmin();

	}

	public void InsertAdmin() throws SQLException {
		Connection connection = null;
		connection = ConnectionUtil.getConnection();
		String query = "Insert into Admin values(?,?,?,?,?,?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Adminid);
		preparedStatement.setString(2, AdmName);
		preparedStatement.setString(3, AdmAddress);
		preparedStatement.setLong(4, AdmContact);
		preparedStatement.setString(5, AdmEmail);
		preparedStatement.setString(6, AdmDesignation);
		preparedStatement.setString(7, AdmDOJ);
		preparedStatement.setInt(8, AdmSalary);
		preparedStatement.setString(9, Adminpassword);
		int update = preparedStatement.executeUpdate();
		System.out.println("Sign Up successfully !");
	}

	public static void RetrivAdmin() throws SQLException, adminException {

		Connection connection = null;
		connection = ConnectionUtil.getConnection();

		Statement statement = connection.createStatement();
		String query = "select admin.adminid,admin.adminpassword from admin;";
		ResultSet rs = statement.executeQuery(query);

		int i = 0;
		while (rs.next()) {
			if (ID == (rs.getInt(1)) && Adminpassword.equals(rs.getString(2))) {
				System.out.println("Login Successfully" + "\n");
				System.out.println("-----------------<*>----------------");
				i++;
			}
		}
		if (i == 0) {
			System.out.println("\nAdmin Details are not available, Please enter a valid ID!!");
			// explicitly threow Admin exception
			throw new adminException("Please enter valid ID And Password !");
		}
	}

	public static void profile() throws SQLException {
		Connection connection = null;
		connection = ConnectionUtil.getConnection();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM EMS.Employee order by Employeeid asc;";

		ResultSet resultSet = statement.executeQuery(query);
		System.out.println("________________Employee Table ________________");
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Address", "Contact No",
				"Email", "Designation", "DOJ", "Salary", "Password");

		while (resultSet.next()) {
			System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", resultSet.getInt(1),
					resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
					resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
		}
		connection.close();
	}

	public static void searchemployee() throws SQLException, adminException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee ID to search :");
		int Checkid = scanner.nextInt();

		Connection connection = null;
		connection = ConnectionUtil.getConnection();
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM EMS.Employee where  Employeeid =" + Checkid + ";";
		int i = 0;
		ResultSet resultSet = statement.executeQuery(query);
		System.out.println("________________Employee Details Table ________________");
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Address", "Contact No",
				"Email", "Designation", "DOJ", "Salary", "Password");

		while (resultSet.next()) {
			System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", resultSet.getInt(1),
					resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
					resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));

			i++;
		}
		if (i == 0) {
			System.out.println("\\nEmployee Details are not available, Please enter a valid ID!!");

		}
	}
}
