package learning_db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;

import learning_db.dbmanagers.DBType;
import learning_db.dbmanagers.DBUtil;
import learning_db.util.InputHelper;

public class StoredProcOut {

	private static final String SQL = "{call GetToursWithCountByPrice(?, ?)}";

	public static void main(String[] args) throws Exception {
		double maxPrice;

		try {
			maxPrice = InputHelper.getDoubleInput("Enter a maximum price: ");
		} catch (NumberFormatException e) {
			System.err.println("Error: invalid number");
			return;
		}

		ResultSet rs = null;
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				CallableStatement stmt = conn.prepareCall(SQL,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			stmt.setDouble(1, maxPrice);
			stmt.registerOutParameter("total", Types.INTEGER);
			rs = stmt.executeQuery();

			int nRows = stmt.getInt("total");

			if (nRows == 0) {
				System.out.println("No tours were found");
			} else {
				System.out.println("Number of tours: " + nRows);
				rs.beforeFirst();

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

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (rs != null) rs.close();
		}
	}

}
