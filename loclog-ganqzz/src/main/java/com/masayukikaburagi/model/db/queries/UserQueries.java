package com.masayukikaburagi.model.db.queries;

public interface UserQueries {
    String getAll();
    String getUsersCount();
    String getUserByName();
    String createUser();
    String updateUser();
    String deleteUserByName();
}
