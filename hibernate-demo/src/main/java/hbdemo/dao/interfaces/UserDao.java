package hbdemo.dao.interfaces;

import hbdemo.entities.User;

import java.util.List;

public interface UserDao extends Dao<User, Long> {

    List<User> findByFirstName(String firstName);
}
