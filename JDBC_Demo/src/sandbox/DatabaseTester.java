package sandbox;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

public class DatabaseTester {

	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ganq", "ganq",
					"hawkeye");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT id, name, password FROM users");
			boolean hasResults = rs.first();
			String id = null;
			String firstName = null;
			String lastName = null;
			while (hasResults) {
				id = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(id + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
