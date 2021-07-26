package learning_db;

import learning_db.tables.AdminManager;
import learning_db.util.InputHelper;

public class Delete {

	public static void main(String[] args) throws Exception {
		AdminManager.displayAllRows();
		int adminId = InputHelper.getIntegerInput("Select a row to delete: ");

		if (AdminManager.delete(adminId)) {
			System.out.println("Success!");
		} else {
			System.err.println("Nothing to delete!");
		}

	}
}
