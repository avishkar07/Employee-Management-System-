package EMS;
import java.util.Scanner;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class Employee extends abstractclass {
	// -------------Inheritance--------------
	private static String EmpName;
	private static String EmpAddress;
	private static String EmpDOJ;
	private static long EmpContact;
	private static String EmpEmail;
	private static String EmpDesignation;
	private static int EmpSalary;
	private static int Employeeid;
	private static String Employeepassword;
	private static int ID;

	@Override
	public String toString() {
		return "Employee [EmpName=" + EmpName + ", EmpAddress=" + EmpAddress + ", EmpDOJ=" + EmpDOJ + ", EmpContact="
				+ EmpContact + ", EmpEmail=" + EmpEmail + ", EmpDesignation=" + EmpDesignation + ", ID=" + ID + "]";
	}

	// -----------Encapsulation-------------
	// getter method
	public String getEmpName() {
		return EmpName;
	}

	// setting method
	public void setEmpName(String empName) {
		EmpName = empName;
	}

	// getter method
	public String getEmpAddress() {
		return EmpAddress;
	}

	// setting method
	public void setEmpAddress(String empAddress) {
		EmpAddress = empAddress;
	}

	// getter method
	public String getEmpDOJ() {
		return EmpDOJ;
	}

	public void setEmpDOJ(String empDOJ) {
		EmpDOJ = empDOJ;
	}

	public long getEmpContact() {
		return EmpContact;
	}

	public void setEmpContact(long empContact) {
		EmpContact = empContact;
	}

	public String getEmpEmail() {
		return EmpEmail;
	}

	public void setEmpEmail(String empEmail) {
		EmpEmail = empEmail;
	}

	public String getEmpDesignation() {
		return EmpDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		EmpDesignation = empDesignation;
	}

	public int getEmpSalary() {
		return EmpSalary;
	}

	public void setEmpSalary(int empSalary) {
		EmpSalary = empSalary;
	}

	public int getEmployeeid() {
		return Employeeid;
	}

	public void setEmployeeid(int employeeid) {
		Employeeid = employeeid;
	}

	public String getEmployeepassword() {
		return Employeepassword;
	}

	public void setEmployeepassword(String employeepassword) {
		Employeepassword = employeepassword;
	}

	Scanner scanner = new Scanner(System.in);

	// Override the EmployeeMethod
	@Override
	public void EmployeeMethod() throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose :");
		System.out.println("1 for Employee login ");
		System.out.println("2 for Employee Sign up");
		int key = scanner.nextInt();

		switch (key) {
		case 1: {
			System.out.println("-------Employee Login-------");
			Employeelogin();
			break;
		}
		case 2: {
			System.out.println("------Employee Sign Up--------");
			Employeesignup();
			EmployeeMethod();
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}


	public static void Employeelogin() throws SQLException, EmployeeException, FileNotFoundException  {

		ArrayList<Employee> employeearray = new ArrayList<Employee>();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee Id:");
		ID = scanner.nextInt();
		System.out.println("Enter the Password");
		Employeepassword = scanner.next();

		Connection connection = null;
		connection = ConnectionUtil.getConnection();

		Statement statement = connection.createStatement();
		String query = "select Employee.Employeeid,Employee.Employeepassword from Employee;";
		ResultSet rs = statement.executeQuery(query);
		int i = 0;
		while (rs.next()) {
			if (ID == (rs.getInt(1)) && Employeepassword.equals(rs.getString(2))) {
				System.out.println("Login Successfully" + "\n");
				System.out.println("-----------------<*>----------------");
				i++;
			}

		}
		if (i == 0) {
			System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
			// explicitly threow Employee exception
			throw new EmployeeException("Please enter valid ID And Password !");
		}
		int status1 = 0;
		while (status1 == 0) {
			System.out.println("\n*********Welcome**********\n");
			System.out.println("1). Profile\n" + "2). Edit Employee details\n" + "3). Salary\n" + "4). Attendace\n"
					+ "5). EXIT\n");

			System.out.println("Enter your choice : ");
			int key = scanner.nextInt();

			switch (key) {
			case 1:
				System.out.println("Employee Details :");
				Profile();
				break;
			case 2:
				int j = 0;
				do {
					int ch1 = 0;

					System.out.println("\nEDIT Employee Details :\n" + "1). Employee ID\n" + "2). Name\n"
							+ "3). Contact No.\n" + "4). Email-ID\n" + "5). Profile photo\n"+"6). Password\n" + "7). GO BACK\n");
					System.out.println("Enter your choice : ");
					ch1 = scanner.nextInt();

					switch (ch1) {
					case 1:
						System.out.println("\nEnter new Employee ID:");
						int newId = scanner.nextInt();
						String query1 = "UPDATE Employee " + "SET employeeid =" + newId + " WHERE employeeid =" + ID;
						statement.executeUpdate(query1);
						System.out.println("Employee ID	Updated");
						System.out.println("____________________________________________");
						break;

					case 2:
						System.out.println("Enter new Employee Name:");
						String newname = scanner.next();
						String queryname = "UPDATE Employee " + "SET EmpName ='" + newname + "' WHERE employeeid ="
								+ ID;
						statement.executeUpdate(queryname);
						System.out.println("Name Updated");
						System.out.println("____________________________________________");

						break;

					case 3:
						System.out.println("Enter new Employee Contact No. :");
						long contact_no = scanner.nextLong();
						String querycontact = "UPDATE Employee " + "SET Empcontact =" + contact_no
								+ " WHERE employeeid =" + ID;
						statement.executeUpdate(querycontact);
						System.out.println("Contact No	Updated");
						System.out.println("____________________________________________");
						break;

					case 4:
						System.out.println("Enter new Employee Email-ID :");
						String email_id = scanner.next();
						String queryemail = "UPDATE Employee " + "SET EmpEmail ='" + email_id + "' WHERE employeeid ="
								+ ID;
						statement.executeUpdate(queryemail);
						System.out.println("Email-ID Updated");
						System.out.println("____________________________________________");
						break;
					case 5:
						System.out.println("Enter the path of profile :");
						String path = "C:\\Users\\AvishkarMulik\\Downloads\\Photo Avishkar.jpg";
//						String query = "INSERT INTO Employee(profile) values (?);";

						PreparedStatement statement1 = connection.prepareStatement(query);
				
						File file = new File(path);
						FileInputStream fileInputStream = new FileInputStream(file);
			
						statement1.setBlob(1,fileInputStream);

//						int  row= statement.executeUpdate();
						System.out.println(" file uploaded");
					case 6:
						System.out.println("Enter new Employee Password :");
						String pass = scanner.next();
						String querypassword = "UPDATE Employee " + "SET Employeepassword ='" + pass
								+ "' WHERE employeeid =" + ID;
						statement.executeUpdate(querypassword);
						System.out.println("Password Updated");
						System.out.println("____________________________________________");
						break;

					case 7:
						j++;
						break;

					default:
						System.out.println("\nEnter a correct choice from the List :");
						System.out.println("____________________________________________");
						break;
					}
				} while (j == 0);

			case 3:
				String query1 = "SELECT * FROM EMS.salary where  Empid =" + ID + ";";
				int k = 0;
				ResultSet resultSet = statement.executeQuery(query1);
				System.out.println("________________Salary Table ________________");
				System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "GrossSalary", "IncomeTax",
						"ProfessionalTax", "ProvidentFund", "Netsalary");

				while (resultSet.next()) {
					System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n", resultSet.getInt(1), resultSet.getDouble(2),
							resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5),
							resultSet.getDouble(6));
					k++;
				}
				if (k == 0) {
					System.out.println("\\nEmployee Details are not available, Please enter a valid ID!!");

				}
				break;
			case 4:

				System.out.println("Enter the Name Of employee :");
				String Name = scanner.next();
				System.out.println("Enter the Date :");
				String date = scanner.next();
				System.out.println("Enter the Satus : \n 1 for present \n 2 for absent\n");
				int key1 = scanner.nextInt();
				String status;
				switch (key1) {
				case 1: {
					status = "present";
					break;
				}
				case 2: {
					status = "Absent";
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + key1);
				}

				Connection con = null;
				con = ConnectionUtil.getConnection();
				String queryattendace = "Insert into attendace value(?,?,?,?);";
				PreparedStatement preparedStatement = con.prepareStatement(queryattendace);
				preparedStatement.setInt(1, ID);
				preparedStatement.setString(2, Name);
				preparedStatement.setString(3, date);
				preparedStatement.setString(4, status);

				int update = preparedStatement.executeUpdate();
				System.out.println("Attendace Inserted -" + status);
				break;
			case 5:
				System.out.println("\nYou have chosen EXIT !!");
				System.out.println("________________________________________");
				status1++;
//				scanner.close();
//				System.exit(0);
//				try {
//					thread.stop();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				break;
			}

		}
	}

	public void Employeesignup() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID");
		Employeeid = scanner.nextInt();
		System.out.println(" Name :");
		EmpName = scanner.next();
		System.out.println("Address :");
		EmpAddress = scanner.next();
		System.out.println("Date Of Birth :");
		EmpDOJ = scanner.next();
		System.out.println("Contact No :");
		EmpContact = scanner.nextLong();
		System.out.println("Email :");
		EmpEmail = scanner.next();
		System.out.println("Designation :");
		EmpDesignation = scanner.next();
		System.out.println("Salary :");
		EmpSalary = scanner.nextInt();
		System.out.println("Password");
		Employeepassword = scanner.next();
		try {
			InsertEmployee();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InsertEmployee() throws SQLException {

		Connection connection = null;
		connection = ConnectionUtil.getConnection();
		String query = "Insert into Employee values(?,?,?,?,?,?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Employeeid);
		preparedStatement.setString(2, EmpName);
		preparedStatement.setString(3, EmpAddress);
		preparedStatement.setLong(4, EmpContact);
		preparedStatement.setString(5, EmpEmail);
		preparedStatement.setString(6, EmpDesignation);
		preparedStatement.setString(7, EmpDOJ);
		preparedStatement.setInt(8, EmpSalary);
		preparedStatement.setString(9, Employeepassword);
		int update = preparedStatement.executeUpdate();
		System.out.println(" Sign Up successfully ! ");

	}

	public static void Profile() throws SQLException {

		// ---------------Arraylist-------------------

		ArrayList<Employee> arrayList = new ArrayList<Employee>();
		try {
			Connection connection = null;
			connection = ConnectionUtil.getConnection();

			Statement statement = connection.createStatement();
			String query = "SELECT * FROM EMS.Employee where employeeid=" + ID + ";";

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				Employee employee = new Employee();

				employee.setEmployeeid(resultSet.getInt("employeeId"));
				employee.setEmpName(resultSet.getString("empName"));
				employee.setEmpAddress(resultSet.getString("empAddress"));
				employee.setEmpDOJ(resultSet.getString("empDOJ"));
				employee.setEmpContact(resultSet.getLong("empContact"));
				employee.setEmpEmail(resultSet.getString("empemail"));
				employee.setEmpDesignation(resultSet.getString("empdesignation"));
				arrayList.add(employee);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		// --------------Iterator---------------

		Iterator<Employee> iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());

		}

//		Connection connection = null;
//		connection = ConnectionUtil.getConnection();
//		Statement statement = connection.createStatement();
//		String query = "SELECT * FROM EMS.Employee where employeeid = " + ID + ";";
//
//		ResultSet resultSet = statement.executeQuery(query);
//		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Address", "Contact No",
//				"Email", "Designation", "DOJ", "Salary", "Password");
//
//		while (resultSet.next()) {
//			System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", resultSet.getInt(1),
//					resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
//					resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9));
//		}
//		connection.close();

	}

}
