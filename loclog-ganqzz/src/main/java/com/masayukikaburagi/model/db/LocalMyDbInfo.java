package com.masayukikaburagi.model.db;

/**
 * ローカル環境 MySQL Information
 *
 * @author 鏑木雅之
 */
public class LocalMyDbInfo extends DbInfo {

    public LocalMyDbInfo() {
        setUsername("ganq");
        setPassword("hawkeye");
        setDbUrl("jdbc:mysql://localhost/ganq");
        setDriver("com.mysql.jdbc.Driver");
//		setLlq(new LoclogQueriesMy());
//		setUq(new UserQueriesMy());
    }
}
