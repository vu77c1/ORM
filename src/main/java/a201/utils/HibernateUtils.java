package a201.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import a201.entities.MoVie;
import a201.entities.MovieType;
import a201.entities.Type;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    static {
        // Create a new Configuration object
        Configuration cfg = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pros.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pros.put(Environment.URL,
                "jdbc:sqlserver://localhost:1433;database=movie ;TrustServerCertificate=true;");
        pros.put(Environment.USER, "sa");
        pros.put(Environment.PASS, "Tuanvu1993@");
        pros.put(Environment.HBM2DDL_AUTO, "update");
        // pros.put(Environment.SHOW_SQL, "true");

        cfg.setProperties(pros);

        // Add entity classes to the configuration
        cfg.addAnnotatedClass(Type.class);
        cfg.addAnnotatedClass(MoVie.class);
        cfg.addAnnotatedClass(MovieType.class);

        // Get the SessionFactory object from Configuration
        if (sessionFactory == null) {
            sessionFactory = cfg.buildSessionFactory();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
