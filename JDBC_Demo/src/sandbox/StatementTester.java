package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTester {

	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/ganq", "ganq", "hawkeye");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("Select id, first_name, last_name "
					+ "from people where first_name like 'H%'");
			boolean hasResults = rs.first();
			String ID = null;
			String firstName = null;
			String lastName = null;
			while (hasResults) {
				ID = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(ID + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}

			System.out.println("=== Before Insert ===");
			rs = stmt.executeQuery("Select id, first_name, last_name from people");
			hasResults = rs.first();
			while (hasResults) {
				ID = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(ID + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}
			System.out.println();

			conn.setAutoCommit(false);
			boolean result = stmt
					.execute(
							"insert into people (id, first_name, last_name) values ('7', 'vtc', 'Author')");
			conn.commit();
			result = stmt
					.execute(
							"insert into people (id, first_name, last_name) values ('8', 'Test', 'Rollback')");
			conn.rollback();

			System.out.println("=== After Insert, Commit, Insert, Rollback ===");
			rs = stmt.executeQuery("Select id, first_name, last_name from People");
			hasResults = rs.first();
			while (hasResults) {
				ID = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(ID + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}

			conn.setAutoCommit(true);
			result = stmt.execute("delete from people where id = '7'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
