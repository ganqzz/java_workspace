package learning_db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import learning_db.dbmanagers.DBType;
import learning_db.dbmanagers.DBUtil;

public class Scrollable {

	public static void main(String[] args) throws Exception {

		try (Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(
						"SELECT stateId, stateName FROM states");) {

			while (rs.next()) {
				String stateFullName = rs.getString("stateId") + ": " + rs.getString("stateName");
				System.out.println(stateFullName);
			}

			rs.last();
			System.out.println("Number of rows: " + rs.getRow());

			rs.first();
			System.out.println("The first state is " + rs.getString("stateName"));

			rs.last();
			System.out.println("The last state is " + rs.getString("stateName"));

			rs.absolute(10);
			System.out.println("The 10th state is " + rs.getString("stateName"));

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

}
