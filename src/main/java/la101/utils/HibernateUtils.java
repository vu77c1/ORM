package la101.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import la101.entities.Appointment;
import la101.entities.Bill;
import la101.entities.Docter;
import la101.entities.Patient;
import la101.entities.Payment;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    static {
        // Create a new Configuration object
        Configuration cfg = new Configuration();
        Properties pros = new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        pros.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        pros.put(Environment.URL,
                "jdbc:sqlserver://localhost:1433;database=clinicdb ;TrustServerCertificate=true;");
        pros.put(Environment.USER, "sa");
        pros.put(Environment.PASS, "Tuanvu1993@");
        pros.put(Environment.HBM2DDL_AUTO, "update");
        // pros.put(Environment.SHOW_SQL, "true");

        // Thiết lập Log4j
        // pros.put(Environment.SHOW_SQL, "true");
        pros.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        cfg.setProperties(pros);

        cfg.addAnnotatedClass(Docter.class);
        cfg.addAnnotatedClass(Appointment.class);
        cfg.addAnnotatedClass(Patient.class);
        cfg.addAnnotatedClass(Payment.class);
        cfg.addAnnotatedClass(Bill.class);

        // Get the SessionFactory object from Configuration
        if (sessionFactory == null) {
            sessionFactory = cfg.buildSessionFactory();
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
