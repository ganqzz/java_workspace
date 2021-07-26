package sandbox;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTester {

	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ganq", "ganq",
					"hawkeye");

			// Database metadata
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println("== MetaData ==");
			System.out.println("Product : " + metaData.getDatabaseProductName() + " "
					+ metaData.getDatabaseProductVersion());
			System.out.println("Version : " + metaData.getDatabaseMajorVersion() + "."
					+ metaData.getDatabaseMinorVersion());
			System.out.println("Non SQL2003 keywords : " + metaData.getSQLKeywords());
			System.out.println("== MetaData ==");
			System.out.println();

			// Auto commit
			System.out.println("Autocommit = " + conn.getAutoCommit());

			// Cursors hold over commit
			if (conn.getHoldability() == ResultSet.HOLD_CURSORS_OVER_COMMIT) {
				System.out.println("Cursor will hold over commit");
			} else {
				System.out.println("Cursor will close over commit");
			}

			// Transaction isolation
			int isolation = conn.getTransactionIsolation();
			if (isolation == Connection.TRANSACTION_NONE) {
				System.out.println("Transactions currently not supported");
			} else if (isolation == Connection.TRANSACTION_READ_UNCOMMITTED) {
				System.out.println("Transactions currently READ UNCOMMITTED");
			} else if (isolation == Connection.TRANSACTION_READ_COMMITTED) {
				System.out.println("Transactions currently READ COMMITTED");
			} else if (isolation == Connection.TRANSACTION_REPEATABLE_READ) {
				System.out.println("Transactions currently REPEATABLE READ");
			} else if (isolation == Connection.TRANSACTION_SERIALIZABLE) {
				System.out.println("Transactions currently SERIALIZABLE");
			} else {
				System.out.println("Transactions currently status unknown");
			}

			Statement stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
