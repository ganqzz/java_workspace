package com.masayukikaburagi.model.dao;

import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.entity.Loclog;

import java.util.ArrayList;

/**
 * Loclog DAO
 * データストア（RDBMS、NoSQL、ファイル）に依存しない。
 *
 * @author 鏑木雅之
 */
public interface LoclogDao {
    ArrayList<Loclog> getLoclogsList(String userName, int offset, int count);
    int getLoclogsCountByName(String userName);
    long getLastMtime(String userName);
    Loclog getLoclogById(String userName, int logId);
    String getLoclogImageById(String userName, int logId);
    boolean createLoclog(Loclog loclog) throws DataAccessException;
    boolean updateLoclog(Loclog loclog) throws DataAccessException;
    boolean deleteLoclogById(String userName, int logId) throws DataAccessException;
    ArrayList<Loclog> export();
}
