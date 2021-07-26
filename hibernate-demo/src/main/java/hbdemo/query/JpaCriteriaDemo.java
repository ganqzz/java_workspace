package hbdemo.query;

import hbdemo.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class JpaCriteriaDemo {

    public static void main(String[] args) {
        //simpleDemo();
        //filteringDemo();
        pagingDemo();
    }

    private static void simpleDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            // select t from transaction t
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            criteriaQuery.select(root);

            TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
            List<Transaction> transactions = query.getResultList();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
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

    private static void filteringDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            Path<BigDecimal> amountPath = root.get("amount");
            Path<String> transactionTypePath = root.get("transactionType");

            criteriaQuery.select(root).where(
                    cb.and(cb.le(amountPath, new BigDecimal("20.00")),
                           cb.equal(transactionTypePath, "Withdrawl")));

            TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
            List<Transaction> transactions = query.getResultList();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
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

    private static void pagingDemo() {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        int pageNumber = 1;
        int pageSize = 5;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            criteriaQuery.select(root);

            TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            List<Transaction> transactions = query.getResultList();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
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
