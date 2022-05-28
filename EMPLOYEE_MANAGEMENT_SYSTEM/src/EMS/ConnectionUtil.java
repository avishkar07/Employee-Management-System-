package EMS;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/EMS";
		String usr = "root";
		String pass = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, usr, pass);
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
