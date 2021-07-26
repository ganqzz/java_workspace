package hbdemo.dao;

import hbdemo.dao.interfaces.UserDao;
import hbdemo.entities.User;

import java.util.List;

public class UserHibernateDao extends AbstractDao<User, Long> implements UserDao {

    @Override
    public List<User> findByFirstName(String firstName) {
        //add implementation here...
        return null;
    }
}
