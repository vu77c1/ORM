package finals.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    static {
        // Create a new Configuration object
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg2.xml");

        // Get the SessionFactory object from Configuration
        if (sessionFactory == null) {
            sessionFactory = cfg.buildSessionFactory();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
