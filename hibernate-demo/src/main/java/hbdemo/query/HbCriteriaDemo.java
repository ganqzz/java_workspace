package hbdemo.query;

import hbdemo.HibernateUtil;
import hbdemo.entities.Transaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.util.List;

public class HbCriteriaDemo {

    public static void main(String[] args) {
        //simpleDemo();
        //filteringDemo();
        pagingDemo();
    }

    private static void simpleDemo() {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Transaction> transactions = session
                    .createCriteria(Transaction.class)
                    .addOrder(Order.desc("title")).list();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }

    private static void filteringDemo() {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criterion criterion1 = Restrictions.le("amount", new BigDecimal("20.00"));
            Criterion criterion2 = Restrictions.eq("transactionType", "Withdrawl");

            List<Transaction> transactions = session
                    .createCriteria(Transaction.class).add(Restrictions.and(criterion1, criterion2))
                    .addOrder(Order.desc("title")).list();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }

    public static void pagingDemo() {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        int pageNumber = 3;
        int pageSize = 4;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Transaction.class)
                                       .addOrder(Order.asc("title"));
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            List<Transaction> transactions = criteria.list();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }
}
