package learning_db;

import java.sql.Connection;

import learning_db.beans.Admin;
import learning_db.dbmanagers.ConnectionManager;
import learning_db.dbmanagers.DBType;
import learning_db.tables.AdminManager;
import learning_db.util.InputHelper;

public class Transaction {

	public static void main(String[] args) throws Exception {

		System.out.println("Starting application");

		ConnectionManager.getInstance().setDBType(DBType.MYSQL);

		AdminManager.displayAllRows();

		int adminId = 0;
		try {
			adminId = InputHelper.getIntegerInput("Select a row to update: ");
		} catch (NumberFormatException e) {
			System.err.println("Invalid entry");
		}

		Admin bean = AdminManager.getRow(adminId);
		if (bean == null) {
			System.err.println("Row not found");
			return;
		}

		String password = InputHelper.getInput("Enter new password: ");
		bean.setPassword(password);

		Connection conn = ConnectionManager.getInstance().getConnection();
		conn.setAutoCommit(false); // default: true

		if (AdminManager.update(bean)) {
			System.out.println("Success!");
		} else {
			System.err.println("whoops!");
		}

//		conn.commit();
//		System.out.println("Transaction committed");
		conn.rollback();
		System.out.println("Transaction rolled back");

		ConnectionManager.getInstance().close();
	}
}
