package hbdemo;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hibernate native
 */
public class SessionFactoryApp {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        //
        transaction.commit();
        session.close();
    }
}
