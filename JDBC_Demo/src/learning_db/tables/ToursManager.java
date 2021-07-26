package learning_db.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import learning_db.dbmanagers.ConnectionManager;

public class ToursManager {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static void displayAllRows() throws SQLException {

		String sql = "SELECT * FROM tours";
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				buffer.append("Tour " + rs.getInt("tourId") + ": ");
				buffer.append(rs.getString("tourName"));

				double price = rs.getDouble("price");
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formattedPrice = nf.format(price);
				buffer.append(" (" + formattedPrice + ")");

				System.out.println(buffer.toString());
			}
		}
	}
}
