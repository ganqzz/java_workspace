package hbdemo.query;

import hbdemo.entities.Account;
import hbdemo.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpqlDemo {

    public static void main(String[] args) {
        //basicDemo();
        joinDemo();
        //functionDemo();
        //namedQueryDemo();
    }

    private static void basicDemo() {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // HQLと違いposition付きもできる
            TypedQuery<Transaction> query = em.createQuery(
                    "from Transaction t"
                    + " where (t.amount between ?1 and ?2) and t.title like '%s'"
                    + " order by t.title", Transaction.class);

            System.out.println("Please provide the first amount:");
            query.setParameter(1, new BigDecimal(scanner.next()));
            System.out.println("Please provide the second amount:");
            query.setParameter(2, new BigDecimal(scanner.next()));

            List<Transaction> transactions = query.getResultList();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            factory.close();
            scanner.close();
        }
    }

    private static void joinDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            //TypedQuery<Account> query = em
            //        .createQuery("select distinct t.account from Transaction t" +
            //                     " where t.amount > 500 and lower(t.transactionType) = 'deposit'",
            //                     Account.class);
            TypedQuery<Account> query = em
                    .createQuery("select distinct a from Transaction t"
                                 + " join t.account a " +
                                 "where t.amount > 500 and t.transactionType = 'Deposit'",
                                 Account.class);

            List<Account> accounts = query.getResultList();

            for (Account a : accounts) {
                System.out.println(a.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }

    private static void functionDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence
                    .createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Query query = em.createQuery("select distinct t.account.name, " +
                                         "concat(concat(t.account.bank.name, ' '), t.account.bank.address.state)"
                                         + " from Transaction t" +
                                         " where t.amount > 500 and t.transactionType = 'Deposit'");

            List<Object[]> accounts = query.getResultList();

            for (Object[] a : accounts) {
                System.out.println(a[0]);
                System.out.println(a[1]);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }

    private static void namedQueryDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence
                    .createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Query query = em.createNamedQuery("Account.byWithdrawlAmount");
            query.setParameter("amount", new BigDecimal("99"));

            List<Object[]> accounts = query.getResultList();

            for (Object[] a : accounts) {
                System.out.println(a[0]);
                System.out.println(a[1]);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }
}
