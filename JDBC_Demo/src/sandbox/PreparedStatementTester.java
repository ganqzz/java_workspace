package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTester {

	public static void main(String[] args) {

		String sqlSelect = "select id, first_name, last_name from people where first_name like ?";
		String sqlInsert = "insert into people (id, first_name, last_name) values (?, ? ,?)";

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ganq", "ganq",
					"hawkeye");
			PreparedStatement stmt = conn.prepareStatement(sqlSelect,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, "H%"); // シングルクォートは必要ない。
			ResultSet rs = stmt.executeQuery();
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

			System.out.println("=== Before Insert ===");
			stmt = conn.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, "%");
			rs = stmt.executeQuery();
			hasResults = rs.first();
			while (hasResults) {
				id = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(id + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}
			System.out.println();

			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sqlInsert, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, "7");
			stmt.setString(2, "vtc");
			stmt.setString(3, "Author");
			boolean result = stmt.execute();
			conn.commit();
			stmt.setString(1, "8");
			stmt.setString(2, "Test");
			stmt.setString(3, "Rollback");
			result = stmt.execute();
			conn.rollback();

			System.out.println("=== After Insert, Commit, Insert, Rollback ===");
			stmt = conn.prepareStatement(sqlSelect, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, "%");
			rs = stmt.executeQuery();
			hasResults = rs.first();
			while (hasResults) {
				id = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				System.out.println(id + " " + firstName + " " + lastName);
				hasResults = rs.next();
			}

			conn.setAutoCommit(true);
			stmt = conn.prepareStatement("delete from people where id = ?");
			stmt.setString(1, "7");
			result = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
