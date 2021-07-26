package hbdemo.mapping;

import hbdemo.HibernateUtil;
import hbdemo.entities.Address;
import hbdemo.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class EmbeddableDemo {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            Address address = new Address();
            user.setAge(22);
            user.setBirthDate(new Date());
            user.setCreatedBy("Kevin");
            user.setCreatedDate(new Date());
            user.setEmailAddress("kmb3");
            user.setFirstName("kevin");
            user.setLastName("bowersox");
            user.setLastUpdatedBy("kmb");
            user.setLastUpdatedDate(new Date());

            address.setAddressLine1("line 1");
            address.setAddressLine2("line2");
            address.setCity("Philadelphia");
            address.setState("PA");
            address.setZipCode("12345");

            user.setAddress(address);
            session.save(user);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
