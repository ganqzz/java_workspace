package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ganq"; // DB engine must be InnoDB.

	// Database credentials
	static final String USER = "ganq";
	static final String PASS = "hawkeye";

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement updateUser = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String updateString = "UPDATE " + "ganq" + ".users " + "SET name = ? WHERE id = ?";

			conn.setAutoCommit(false);
			updateUser = conn.prepareStatement(updateString);

			updateUser.setString(1, "fuga");
			updateUser.setInt(2, 4);
			updateUser.executeUpdate();

			// ... MORE BUSINESS LOGIC

			// conn.commit();
			throw new SQLException();

		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being rolled back");
					conn.rollback();
				} catch (SQLException excep) {
					excep.printStackTrace();
				}
			}
		} finally {
			if (updateUser != null) {
				updateUser.close();
			}
			conn.setAutoCommit(true);
			try {
				if (conn != null) conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
