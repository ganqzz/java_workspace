package com.masayukikaburagi.model.db.queries;

import java.util.Map;

public interface LoclogQueries {
    String getLoclogsList();
    String getLoclogsCount();
    String getLastMtime();
    String getLoclogById();
    String getLoclogImageById();
    String createLoclog();
    String updateLoclog(Map<String, String> param);
    String deleteLoclog();
    String exportLoclogDb();
}
