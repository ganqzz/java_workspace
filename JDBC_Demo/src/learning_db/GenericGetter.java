package learning_db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import learning_db.dbmanagers.DBType;
import learning_db.dbmanagers.DBUtil;

/**
 * Generic Getter
 */
public class GenericGetter {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM tours");) {

			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();

				int tourId = rs.getObject("tourId", Integer.class);
				String tourName = rs.getObject("tourName", String.class);
//				Double price 	= rs.getObject("price", Double.class); // NG: HSQLDB そもそも型がDECIMAL
				BigDecimal price = rs.getObject("price", BigDecimal.class); // MySQL doubleからの変換

//				int tourId 		= rs.getInt("tourId");
//				String tourName = rs.getString("tourName");
//				double price 	= rs.getDouble("price");　// HSQLDB: DECIMALからdoubleへの変換

				buffer.append("Tour " + tourId + ": ");
				buffer.append(tourName);

				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formattedPrice = nf.format(price);
				buffer.append(" (" + formattedPrice + ")");

				System.out.println(buffer.toString());
			}
		} catch (SQLException e) {
			DBUtil.processException(e);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
