package com.masayukikaburagi.model.dao;

import com.masayukikaburagi.model.DataAccessException;
import com.masayukikaburagi.model.db.DbManager;
import com.masayukikaburagi.model.db.queries.UserQueries;
import com.masayukikaburagi.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * User RDBMS汎用（JDBC） DAO
 *
 * @author 鏑木雅之
 */
public class UserRdbDao implements UserDao {

    private DbManager dbm; // DbManagerインスタンス
    private UserQueries uq; // Userモデル用クエリ

    /**
     * Constructor
     *
     * @param dbm
     */
    public UserRdbDao(DbManager dbm) {
        this.dbm = dbm;
        uq = dbm.getDbi().getUq();
    }

    @Override
    public int getUsersCount() {
        return 0;
    }

    /**
     * GET User 詳細
     *
     * @param userName
     * @return
     */
    @Override
    public User getUserByName(String userName) {
        String query = uq.getUserByName();
        User user = new User();

        try (Connection conn = dbm.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery()) {
                // TODO: NULLの扱いが面倒
                if (rs.next()) {
                    user.setUserName(rs.getString("user_name"));
                    user.setPassword(rs.getString("password"));
                    user.setApiKey(rs.getString("api_key"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getShort("role"));
                    user.setCreatedAt(rs.getTimestamp("created_at").getTime());
                    user.setUpdatedAt(rs.getTimestamp("updated_at").getTime());

                    return user;
                } else {
                    // Not found
                    return null;
                }
            }
        } catch (Exception e) {
            // log it
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createUser(User user) throws DataAccessException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteUserByName(String userName) throws DataAccessException {
        return false;
    }
}
