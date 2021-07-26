package hbdemo.mapping;

import hbdemo.HibernateUtil;
import hbdemo.entities.User;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.Date;

public class FormulaDemo {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            User user = new User();
            user.setBirthDate(getMyBirthday());
            user.setCreatedBy("kevin");
            user.setCreatedDate(new Date());
            user.setEmailAddress("kmb385@yahoo.com");
            user.setFirstName("Kevin");
            user.setLastName("Bowersox");
            user.setLastUpdatedBy("kevin");
            user.setLastUpdatedDate(new Date());

            session.save(user);
            session.getTransaction().commit();

            session.refresh(user); // database側での変更（Formula）を同期

            System.out.println(user.getAge());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static Date getMyBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1984);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);
        return calendar.getTime();
    }
}
