package hbdemo.query;

import hbdemo.HibernateUtil;
import hbdemo.entities.Account;
import hbdemo.entities.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class HqlDemo {

    public static void main(String[] args) {
        //basicDemo();
        functionDemo();
        //lazyLoadingDemo();
    }

    private static void basicDemo() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select t from Transaction t " +
                                              "where t.amount > :amount and t.transactionType = 'Withdrawl'");
            System.out.println("Please specify an amount:");

            query.setParameter("amount", new BigDecimal(scanner.next()));
            List<Transaction> transactions = query.list();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            factory.close();
            //scanner.close();
        }
    }

    private static void functionDemo() {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            // associationによるjoin
            Query query = session.createQuery("select distinct t.account from Transaction t" +
                                              " where t.amount > 500 and lower(t.transactionType) = 'deposit'");

            List<Account> accounts = query.list();

            for (Account a : accounts) {
                System.out.println(a.getName());
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

    private static void lazyLoadingDemo() {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Query query = session.getNamedQuery("Account.largeDeposits");
            List<Account> accounts = query.list();
            System.out.println("Query has been executed.");

            for (Account a : accounts) {
                System.out.println(a.getName());
                System.out.println(a.getBank().getName()); //
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
