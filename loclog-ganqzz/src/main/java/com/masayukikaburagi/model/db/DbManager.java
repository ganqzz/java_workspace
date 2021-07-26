package com.masayukikaburagi.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB管理クラス (DbManager)
 *
 * @author 鏑木雅之
 */
public class DbManager {
    private static DbManager instance; // Singleton
    private Connection conn;
    private DbInfo dbi;

    /**
     * Default Constructor
     * テスト用
     */
    private DbManager() {
        // Default DbInfo
        this(new LocalPgDbInfo());
    }

    /**
     * Constructor
     *
     * @param dbi
     */
    private DbManager(DbInfo dbi) {
        this.dbi = dbi;
    }

    /**
     * getInstance()
     * テスト用
     *
     * @return
     */
    public static DbManager getInstance() {
        return getInstance(new LocalPgDbInfo());
    }

    /**
     * getInstance(DbInfo)
     */
    public static synchronized DbManager getInstance(DbInfo dbi) {
        instance = instance == null ? new DbManager(dbi) : instance;
        return instance;
    }

    /**
     * @return the dbi
     */
    public DbInfo getDbi() {
        return dbi;
    }

    /**
     * @return the Connection
     */
    public Connection getConnection() {
        if (conn != null) closeConnection(); // 一旦切断する。

        try {
            Class.forName(dbi.getDriver());
            conn = DriverManager.getConnection(
                    dbi.getDbUrl(),
                    dbi.getUsername(),
                    dbi.getPassword());
            conn.setAutoCommit(false); // 自動コミットoff（Transactionを使用する）
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            conn = null;
        }

        return conn;
    }

    /**
     * Close Connection
     *
     * @return
     */
    public boolean closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        conn = null;
        return true;
    }
}
