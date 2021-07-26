package com.masayukikaburagi.model.db.queries;

public class UserQueriesPg implements UserQueries {
    @Override
    public String getAll() {
        return "SELECT user_name, role FROM users";
    }

    @Override
    public String getUsersCount() {
        return "SELECT count(*) FROM users";
    }

    @Override
    public String getUserByName() {
        return "SELECT * FROM users WHERE user_name = ?";
    }

    @Override
    public String createUser() {
        return "INSERT INTO users VALUES ()";
    }

    @Override
    public String updateUser() {
        return "UPDATE users SET WHERE user_name = ?";
    }

    @Override
    public String deleteUserByName() {
        return "DELETE FROM users WHERE user_name = ?";
    }

}
