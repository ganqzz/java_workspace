package learning_db;

import learning_db.beans.Admin;
import learning_db.tables.AdminManager;
import learning_db.util.InputHelper;

public class Insert {

	public static void main(String[] args) throws Exception {

		AdminManager.displayAllRows();
		
		Admin bean = new Admin();
		bean.setUserName(InputHelper.getInput("User name: "));
		bean.setPassword(InputHelper.getInput("Password: "));
		
		boolean result = AdminManager.insert(bean);
		
		if (result) {
			System.out.println("New row with primary key " + bean.getAdminId() + " was inserted!");
		}
	}

}
