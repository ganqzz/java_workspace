package com.masayukikaburagi.model.dao;

import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.entity.User;

/**
 * User DAO
 *
 * @author ιζ¨ιδΉ
 */
public interface UserDao {
    int getUsersCount();
    User getUserByName(String userName);
    boolean createUser(User user) throws DataAccessException;
    boolean updateUser(User user) throws DataAccessException;
    boolean deleteUserByName(String userName) throws DataAccessException;
}
