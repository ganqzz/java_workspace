package learning_db;

import java.sql.SQLException;

import learning_db.beans.Admin;
import learning_db.tables.AdminManager;
import learning_db.util.InputHelper;

public class GetRow {

	public static void main(String[] args) throws SQLException {
		AdminManager.displayAllRows();
		int adminId = InputHelper.getIntegerInput("Select a row: ");
		Admin bean = AdminManager.getRow(adminId);

		if (bean == null) {
			System.err.println("No rows were found");
		} else {
			System.out.println("Admin id: " + bean.getAdminId());
			System.out.println("User name: " + bean.getUserName());
			System.out.println("Password: " + bean.getPassword());
		}

	}

}
