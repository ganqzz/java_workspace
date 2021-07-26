package hbdemo.persistence;

import hbdemo.HibernateUtil;
import hbdemo.entities.Account;
import hbdemo.entities.Address;
import hbdemo.entities.Bank;
import hbdemo.entities.Credential;
import hbdemo.entities.Transaction;
import hbdemo.entities.User;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Hibernate native CRUD demo
 */
public class PersistenceDemo {

    public static void main(String[] args) {
        saveDemo();
        retrieveDemo();
        modifyDemo();
        removeDemo();
        reattachDemo();
        saveOrUpdateDemo();
        flushDemo();
    }

    private static void saveDemo() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Account account = createNewAccount();
        Transaction trans1 = createNewBeltPurchase(account);
        Transaction trans2 = createShoePurchase(account);
        account.getTransactions().add(trans1);
        account.getTransactions().add(trans2);

        System.out.println(session.contains(account));
        System.out.println(session.contains(trans1));
        System.out.println(session.contains(trans1));

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            session.save(account);

            System.out.println(session.contains(account));
            System.out.println(session.contains(trans1));
            System.out.println(session.contains(trans1));

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void retrieveDemo() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Bank bank = session.load(Bank.class, 1L); // lazy loading by proxy
            System.out.println("Method Executed");

            System.out.println(bank.getName());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void modifyDemo() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Bank bank = session.get(Bank.class, 10L);

            bank.setName("New Hope Bank");
            bank.setLastUpdatedBy("Kevin Bowersox");
            bank.setLastUpdatedDate(new Date());

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void removeDemo() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Bank bank = session.get(Bank.class, 10L);

            System.out.println(session.contains(bank));
            session.delete(bank);
            System.out.println("Method Invoked");
            System.out.println(session.contains(bank));

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void reattachDemo() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank = session.get(Bank.class, 10L);
            transaction.commit();
            session.close();

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction2 = session2.beginTransaction();

            System.out.println(session2.contains(bank));
            session2.update(bank);
            bank.setName("Test Bank");
            System.out.println("Method Invoked");
            System.out.println(session2.contains(bank));

            transaction2.commit();
            session2.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    public static void saveOrUpdateDemo() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank detachedBank = session.get(Bank.class, 10L);
            transaction.commit();
            session.close();

            Bank transientBank = createBank();

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction2 = session2.beginTransaction();

            session2.saveOrUpdate(transientBank);
            session2.saveOrUpdate(detachedBank);
            detachedBank.setName("Test Bank 2");
            transaction2.commit();
            session2.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void flushDemo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        try {
            Bank bank = session.get(Bank.class, 1L);
            bank.setName("Something Different");
            System.out.println("Calling Flush");
            session.flush(); // databaseへの反映

            bank.setAddressLine1("Another Address Line");
            System.out.println("Calling commit");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback(); // flushした分も戻すためには必要
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static Bank createBank() {
        Bank bank = new Bank();
        bank.setName("First United Federal");
        bank.setAddressLine1("103 Washington Plaza");
        bank.setAddressLine2("Suite 332");
        bank.setAddressType("PRIMARY");
        bank.setCity("New York");
        bank.setCreatedBy("Kevin Bowersox");
        bank.setCreatedDate(new Date());
        bank.setInternational(false);
        bank.setLastUpdatedBy("Kevin Bowersox");
        bank.setLastUpdatedDate(new Date());
        bank.setState("NY");
        bank.setZipCode("10000");
        return bank;
    }

    private static User createUser() {
        User user = new User();
        Address address = createAddress();
        user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin Bowersox");
        user.setCreatedDate(new Date());
        user.setCredential(createCredential(user));
        user.setEmailAddress("test@test.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLastUpdatedBy("system");
        user.setLastUpdatedDate(new Date());
        return user;
    }

    private static Credential createCredential(User user) {
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setUsername("test_username");
        credential.setPassword("test_password");
        return credential;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("101 Address Line");
        address.setAddressLine2("102 Address Line");
        address.setCity("New York");
        address.setState("PA");
        address.setZipCode("10000");
        address.setAddressType("PRIMARY");
        return address;
    }

    private static Transaction createNewBeltPurchase(Account account) {
        Transaction beltPurchase = new Transaction();
        beltPurchase.setAccount(account);
        beltPurchase.setTitle("Dress Belt");
        beltPurchase.setAmount(new BigDecimal("50.00"));
        beltPurchase.setClosingBalance(new BigDecimal("0.00"));
        beltPurchase.setCreatedBy("Kevin Bowersox");
        beltPurchase.setCreatedDate(new Date());
        beltPurchase.setInitialBalance(new BigDecimal("0.00"));
        beltPurchase.setLastUpdatedBy("Kevin Bowersox");
        beltPurchase.setLastUpdatedDate(new Date());
        beltPurchase.setNotes("New Dress Belt");
        beltPurchase.setTransactionType("Debit");
        return beltPurchase;
    }

    private static Transaction createShoePurchase(Account account) {
        Transaction shoePurchase = new Transaction();
        shoePurchase.setAccount(account);
        shoePurchase.setTitle("Work Shoes");
        shoePurchase.setAmount(new BigDecimal("100.00"));
        shoePurchase.setClosingBalance(new BigDecimal("0.00"));
        shoePurchase.setCreatedBy("Kevin Bowersox");
        shoePurchase.setCreatedDate(new Date());
        shoePurchase.setInitialBalance(new BigDecimal("0.00"));
        shoePurchase.setLastUpdatedBy("Kevin Bowersox");
        shoePurchase.setLastUpdatedDate(new Date());
        shoePurchase.setNotes("Nice Pair of Shoes");
        shoePurchase.setTransactionType("Debit");
        return shoePurchase;
    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings Account");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }
}
