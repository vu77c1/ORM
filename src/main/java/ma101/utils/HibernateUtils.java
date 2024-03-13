package ma101.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    static {
        // Create a new Configuration object
        Configuration cfg = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pros.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pros.put(Environment.URL,
                "jdbc:sqlserver://localhost:1433;database=movietheaterdb ;TrustServerCertificate=true;");
        pros.put(Environment.USER, "sa");
        pros.put(Environment.PASS, "Tuanvu1993@");
        pros.put(Environment.HBM2DDL_AUTO, "update");
        pros.put(Environment.SHOW_SQL, "true");

        cfg.setProperties(pros);
        cfg.addAnnotatedClass(ma101.entities.Seat.class);
        cfg.addAnnotatedClass(ma101.entities.Room.class);
        cfg.addAnnotatedClass(ma101.entities.RoomDetail.class);

        // Get the SessionFactory object from Configuration
        if (sessionFactory == null) {
            sessionFactory = cfg.buildSessionFactory();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
