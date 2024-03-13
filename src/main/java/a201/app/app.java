package a201.app;

import org.hibernate.Session;

import a201.utils.HibernateUtils;

public class app {

    public static void main(String[] args) {
        connectDb();

    }

    private static void connectDb() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            if (session != null) {

                System.out.println("Connected to the database successfully.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
