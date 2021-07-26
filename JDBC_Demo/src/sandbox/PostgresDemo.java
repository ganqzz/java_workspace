package sandbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDemo {
	// JDBCドライバ名
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	// データベース接続URL
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	// データベース接続ユーザ
	private static final String DB_USER = "postgres";
	// データベース接続パスワード
	private static final String DB_PASSWORD = "hawkeye";

	// mainメソッド・プログラムはここから開始します
	public static void main(String[] args) {
		// DB接続のためのオブジェクト変数の宣言
		Connection conn = null;
		// ステートメントオブジェクト変数の宣言
		PreparedStatement pstmt = null;
		// 検索結果が格納されるオブジェクト変数の宣言
		ResultSet rs = null;
		try {
			// JDBCドライバの初期化を行う命令
			Class.forName(DRIVER_NAME);
			// DBへの接続を行う命令
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// クエリを行うための準備をする
			pstmt = conn.prepareStatement("SELECT * FROM hogetable");
			// クエリの実行
			rs = pstmt.executeQuery();
			// タイトルの表示
			System.out.println("NAME\tID");
			// 検索結果の取り出し（検索された件数分繰り返す）
			while (rs.next()) {
				// リザルトセットから１行分のデータを取り出す
				String password = rs.getString(1);
				int id = rs.getInt(2);
				// 取り出したデータを表示する
				System.out.println(id + "\t" + password);
			}
			// JDBCドライバの読み込みに失敗した場合の例外
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// DBアクセスに伴う例外
		} catch (SQLException e) {
			e.printStackTrace();
			// 正常時・例外時を問わず行われる処理
		} finally {
			// DBの切断を行う
			try {
				// コネクションオブジェクトが存在する場合のみクローズを行う
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
