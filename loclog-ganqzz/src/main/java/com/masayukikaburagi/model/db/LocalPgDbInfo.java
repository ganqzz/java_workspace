package com.masayukikaburagi.model.db;

import com.masayukikaburagi.model.db.queries.LoclogQueriesPg;
import com.masayukikaburagi.model.db.queries.UserQueriesPg;

/**
 * ローカル環境 PostgreSQL Information
 *
 * @author 鏑木雅之
 */
public class LocalPgDbInfo extends DbInfo {

    public LocalPgDbInfo() {
        setUsername("falcon");
        setPassword("hawkeye");
        setDbUrl("jdbc:postgresql://debian9:5432/loclog");
        setDriver("org.postgresql.Driver");
        setLlq(new LoclogQueriesPg());
        setUq(new UserQueriesPg());
    }
}
