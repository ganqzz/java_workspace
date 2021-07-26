package com.masayukikaburagi.model.db.queries;

import java.util.Map;

/**
 * Loclog Queries
 *
 * @author 鏑木雅之
 */
public class LoclogQueriesPg implements LoclogQueries {
    //
    @Override
    public String getLoclogsList() {
        return "SELECT log_id, tag, address, image_uri, open, updated_at FROM loclogs"
               + " WHERE user_name = ?"
               + " ORDER BY updated_at DESC LIMIT ? OFFSET ?"; // PostgreSQL方言
    }

    @Override
    public String getLoclogsCount() {
        return "SELECT count(*) FROM loclogs WHERE user_name = ?";
    }

    @Override
    public String getLastMtime() {
        return "SELECT updated_at FROM loclogs WHERE user_name = ?" +
               " ORDER BY updated_at DESC LIMIT 1";
    }

    @Override
    public String getLoclogById() {
        return "SELECT log_id, tag, address, latitude, longitude, fix_time, note, image_uri, open,"
               + " created_at, updated_at FROM loclogs WHERE user_name = ? AND log_id = ?";
    }

    @Override
    public String getLoclogImageById() {
        return "SELECT image FROM loclogs WHERE user_name = ? AND log_id = ?";
    }

    @Override
    public String createLoclog() {
        return "INSERT INTO loclogs"
               + " (user_name, tag, address, latitude, longitude, fix_time,"
               + " note, image_uri, image, open, created_at, updated_at)"
               + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";
    }

    @Override
    public String updateLoclog(Map<String, String> param) {
        StringBuilder keySb = new StringBuilder();
        StringBuilder valueSb = new StringBuilder();
        for (String key : param.keySet()) {
            keySb.append(key).append(", ");
            valueSb.append("?, ");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE loclogs SET (")
          .append(keySb)
          .append("open, updated_at) = (")
          .append(valueSb)
          .append("?, now()) WHERE user_name = ? AND log_id = ?");

        return sb.toString();
    }

    @Override
    public String deleteLoclog() {
        return "DELETE FROM loclogs WHERE user_name = ? AND log_id = ?";
    }

    @Override
    public String exportLoclogDb() {
        return "SELECT * FROM loclogs";
    }
}
