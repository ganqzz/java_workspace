package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDerbyDemo {

    private static final String PU_NAME = "demoPU";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PU_NAME);

        saveDemo();
        retrieveDemo();

        factory.close();
    }

    private static void saveDemo() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            Hoge hoge = new Hoge();
            hoge.setDescription("Hoge!");
            em.persist(hoge);

            System.out.println(em.contains(hoge));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void retrieveDemo() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            Hoge hoge = em.getReference(Hoge.class, 1L);
            System.out.println(hoge.getDescription());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
