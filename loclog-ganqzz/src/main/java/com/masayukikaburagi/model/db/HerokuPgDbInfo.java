package com.masayukikaburagi.model.db;

import com.masayukikaburagi.model.db.queries.LoclogQueriesPg;
import com.masayukikaburagi.model.db.queries.UserQueriesPg;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Heroku環境 PostgreSQL Information
 *
 * @author 鏑木雅之
 */
public class HerokuPgDbInfo extends DbInfo {

    public HerokuPgDbInfo() {
        URI dbUri = null;
        try {
            // Heroku環境変数から、URIを取得する。
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String[] unpw = dbUri.getUserInfo().split(":");

        setUsername(unpw[0]);
        setPassword(unpw[1]);
        setDbUrl("jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath());
        setDriver("org.postgresql.Driver");
        setLlq(new LoclogQueriesPg());
        setUq(new UserQueriesPg());
    }
}
