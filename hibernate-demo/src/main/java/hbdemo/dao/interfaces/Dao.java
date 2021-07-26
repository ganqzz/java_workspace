package hbdemo.dao.interfaces;

import org.hibernate.Session;

import java.util.List;

public interface Dao<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

    void flush();

    void clear();

    void setSession(Session session);
}
