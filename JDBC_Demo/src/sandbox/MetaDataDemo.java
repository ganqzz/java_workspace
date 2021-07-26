package sandbox;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetaDataDemo {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ganq";

	// Database credentials
	static final String USER = "ganq";
	static final String PASS = "hawkeye";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String tableName;
			stmt = conn.createStatement();

			ResultSetMetaData rsMetaData;
			ResultSet rsDBInfo;
			ResultSet rsTableInfo;

			DatabaseMetaData dbMetaData = conn.getMetaData(); // Connection MetaData Information.
			// ResultSet of all tables.
			rsDBInfo = dbMetaData.getTables(null, null, null, new String[] {"TABLE"});

			while (rsDBInfo.next()) {
				tableName = rsDBInfo.getString("TABLE_NAME");
				System.out.println(" Table Name :: " + tableName);
				if (tableName != null) {
					rsTableInfo = stmt.executeQuery("SELECT * FROM " + tableName);
					rsMetaData = rsTableInfo.getMetaData(); // Table MetaData Information.

					// Start index# is 1.
					for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
						System.out.print(" Column Name: " + rsMetaData.getColumnName(i));
						System.out.println(", Column Type: " + rsMetaData.getColumnType(i));
					}
					System.out.println();
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Handle errors for JDBC
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException se2) {} // nothing we can do
			try {
				if (conn != null) conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}

	}

}
