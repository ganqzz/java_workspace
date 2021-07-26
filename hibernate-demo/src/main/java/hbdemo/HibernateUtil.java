package hbdemo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate 5
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // service設定
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // hibernate.cfg.xml
                    .build();

            // mappingその他の設定情報
            // 設定ファイルを使うので、buildするだけ
            Metadata metadata = new MetadataSources(registry).buildMetadata();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building the factory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
