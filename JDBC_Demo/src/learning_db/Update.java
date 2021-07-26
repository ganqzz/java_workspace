package learning_db;

import learning_db.beans.Admin;
import learning_db.tables.AdminManager;
import learning_db.util.InputHelper;

public class Update {

	public static void main(String[] args) throws Exception {

		AdminManager.displayAllRows();

		int adminId = InputHelper.getIntegerInput("Select a row to update: ");

		Admin bean = AdminManager.getRow(adminId);
		if (bean == null) {
			System.err.println("Row not found");
			return;
		}

		String password = InputHelper.getInput("Enter new password: ");
		bean.setPassword(password);

		if (AdminManager.update(bean)) {
			System.out.println("Success!");
		} else {
			System.err.println("whoops!");
		}

	}
}
