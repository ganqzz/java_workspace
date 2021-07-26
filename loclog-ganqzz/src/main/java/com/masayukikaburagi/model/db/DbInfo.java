package com.masayukikaburagi.model.db;

import com.masayukikaburagi.model.db.queries.LoclogQueries;
import com.masayukikaburagi.model.db.queries.UserQueries;

/**
 * DB情報
 *
 * @author 鏑木雅之
 */
public abstract class DbInfo {
    private String username;
    private String password;
    private String dbUrl;
    private String driver;
    private LoclogQueries llq; // Loclogモデル用クエリ
    private UserQueries uq; // Userモデル用クエリ

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param dbUrl the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the llq
     */
    public LoclogQueries getLlq() {
        return llq;
    }

    /**
     * @param llq the llq to set
     */
    public void setLlq(LoclogQueries llq) {
        this.llq = llq;
    }

    /**
     * @return the uq
     */
    public UserQueries getUq() {
        return uq;
    }

    /**
     * @param uq the uq to set
     */
    public void setUq(UserQueries uq) {
        this.uq = uq;
    }

}
