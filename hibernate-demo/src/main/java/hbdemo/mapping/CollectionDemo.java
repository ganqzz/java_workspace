package hbdemo.mapping;

import hbdemo.HibernateUtil;
import hbdemo.entities.Bank;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class CollectionDemo {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            Bank bank = new Bank();
            bank.setName("Federal Trust");
            bank.setAddressLine1("33 Wall Street");
            bank.setAddressLine2("Suite 233");
            bank.setCity("New York");
            bank.setState("NY");
            bank.setZipCode("12345");
            bank.setInternational(false);
            bank.setCreatedBy("Kevin");
            bank.setCreatedDate(new Date());
            bank.setLastUpdatedBy("Kevin");
            bank.setLastUpdatedDate(new Date());
            // List
            //bank.getContacts().add("Joe");
            //bank.getContacts().add("Mary");
            // Map
            bank.getContactsMap().put("MANAGER", "Joe");
            bank.getContactsMap().put("TELLER", "Mary");
            session.save(bank);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

}
