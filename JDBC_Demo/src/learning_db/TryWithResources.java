package learning_db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import learning_db.dbmanagers.DBType;
import learning_db.dbmanagers.DBUtil;

/**
 * Java7 AutoClosable try-with-resources
 */
public class TryWithResources {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("SELECT stateId, stateName FROM states");) {

			rs.last();
			System.out.println("Number of rows: " + rs.getRow());

		} catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
}
