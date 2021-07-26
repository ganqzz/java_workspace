package com.masayukikaburagi.controller;

import com.masayukikaburagi.model.db.DbManager;
import com.masayukikaburagi.model.entity.User;
import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.dao.UserDao;
import com.masayukikaburagi.model.dao.UserRdbDao;
import org.json.JSONObject;

import javax.servlet.ServletContext;

/**
 * User Controller
 *
 * @author 鏑木雅之
 */
public class UserController {

    private UserDao dao;

    /**
     * Constructor
     * テスト用
     */
    public UserController() {
        dao = new UserRdbDao(DbManager.getInstance());
    }

    /**
     * Constructor
     *
     * @param sctx
     */
    public UserController(ServletContext sctx) {
        // RDBMS
        dao = new UserRdbDao((DbManager) sctx.getAttribute("DbManager"));
    }

    /**
     * ユーザ数
     *
     * @return
     */
    public String getUsersCount() {
        return new JSONObject().put("count", dao.getUsersCount()).toString();
    }

    /**
     * ユーザ情報検索
     *
     * @param userName
     * @return JSON String
     */
    public String getUserByName(String userName) {
        User user = dao.getUserByName(userName);
        if (user == null) {
            // Not found
            return null;
        }

        // JSON変換
        return new JSONObject(user).toString();
    }

    /**
     * 正規ユーザ判定
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean isLegitimateUser(String userName, String password) {
        User user = dao.getUserByName(userName);
        if (user == null) {
            // Not found
            return false;
        }

        // passwordが一致するかどうか
        return user.getPassword().equals(password);
    }

    public boolean createUser() throws DataAccessException {
        User user = new User();

        return dao.createUser(user);
    }

    public boolean updateUser() throws DataAccessException {
        User user = new User();

        return dao.updateUser(user);
    }

    public boolean deleteUserByName(String userName) throws DataAccessException {
        return dao.deleteUserByName(userName);
    }

}
