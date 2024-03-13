package la101.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ParameterMode;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.Query;
import org.hibernate.result.ResultSetOutput;

import la101.dao.AppointmentDao;
import la101.dao.BillDao;
import la101.dao.PatientDao;
import la101.dao.PaymentDao;
import la101.entities.Appointment;
import la101.entities.Bill;
import la101.entities.Patient;
import la101.entities.Payment;
import la101.enums.BillStatus;
import la101.enums.PaymentMethoad;
import la101.ipl.AppointmentDaoipl;
import la101.ipl.BillDaoipl;
import la101.ipl.PatientDaoipl;
import la101.ipl.PaymentDaoipl;
import la101.utils.ErrorMessages;
import la101.utils.HibernateUtils;

public class app {
    @SuppressWarnings("deprecation")
    static AppointmentDao appointmentDao = new AppointmentDaoipl();
    static PatientDao patientDao = new PatientDaoipl();
    static BillDao billDao = new BillDaoipl();
    static PaymentDao paymentDao = new PaymentDaoipl();
    static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Bill.class);

    public static void main(String[] args) {
        // connectDb();
        // Chuc nang thanh toan
        // Pay();
        // searchBillByDate(LocalDate.now()).forEach(System.out::println);
        // searchPaymentByBill(5).forEach(z -> System.out.println(z));
        // getPagedPatients(1, 2).forEach(System.out::println);
        pagingBill(1, 2).forEach(System.out::println);

    }

    private static void Pay() {
        System.out.println("DANH SACH LICH KHAM: ");
        List<Appointment> list = appointmentDao.findAll();
        list.forEach(System.out::println);

        List<Bill> listBill = billDao.findAll().stream().filter(z -> z.getStatus().equals(BillStatus.Unpaid))
                .collect(Collectors.toList());
        listBill.forEach(System.out::println);

        // Sử dụng Stream để lấy danh sách appointmentId từ danh sách bills
        Set<Integer> appointmentIds = listBill.stream()
                .map(Bill::getAppointment)
                .map(Appointment::getId)
                .collect(Collectors.toSet());

        // In danh sách appointmentIds
        appointmentIds.forEach(System.out::println);

        System.out.println("NHAP MA LICH KHAM CAN THANH TOAN: ");
        int id = scanner.nextInt();

        if (appointmentIds.contains(id)) {
            System.out.println("LICH KHAM DA THANH TOAN NHUNG CON THIEU:");
            Appointment appointment = appointmentDao.findById(id);
            System.out.println(appointment);
            Bill bill = billDao.findAll().stream().filter(z -> z.getAppointment().getId() == id).findFirst()
                    .orElse(null);

            Payment payment = paymentDao.findAll().stream()
                    .filter(z -> z.getBill().getId() == bill.getId())
                    .findFirst()
                    .orElse(null);
            double total = bill.getTotal() - payment.getAmount();
            System.out.println("Tien no: " + total);
            System.out.println("Nhap so tien can thanh toan:");
            double amount = scanner.nextDouble();
            if (amount >= total) {
                bill.setStatus(BillStatus.Paid);
                billDao.update(bill);
                payment.setAmount(bill.getTotal());
                paymentDao.update(payment);
                System.out.println("Thanh toan thanh cong");
            } else {
                payment.setAmount(total - amount);
                paymentDao.update(payment);
                System.out.println("Thanh toan con thieu");

            }

            // so tien no

        } else {

            Appointment appointment = appointmentDao.findById(id);
            System.out.println(appointment);
            System.out.println("NHAP SO TIEN CAN THANH TOAN: ");
            double total = scanner.nextDouble();
            Bill bill = new Bill();
            bill.setTotal(total);
            bill.setStatus(BillStatus.Unpaid);
            bill.setDate(LocalDate.now());
            bill.setAppointment(appointment);
            Bill bill2 = billDao.insert_Id(bill);

            System.out.println("Chon hinh thuc thanh toan: ");
            System.out.println("1. Thanh toan bang tien mat");
            System.out.println("2. Thanh toan bang the");
            int choice = scanner.nextInt();
            Payment payment = new Payment();
            if (choice == 1) {
                payment.setMethod(PaymentMethoad.CASH);
            } else if (choice == 2) {
                payment.setMethod(PaymentMethoad.CARD);
            }
            payment.setDate(LocalDate.now());
            payment.setPatient(patientDao.findById(appointment.getPatient().getId()));
            payment.setBill(bill2);
            System.out.println("So tien cua bill ban la: " + total);
            System.out.println("Nhap so tien ban can thanh toan: ");
            double total2 = scanner.nextDouble();
            payment.setAmount(total2);
            if (total2 >= total) {
                bill2.setStatus(BillStatus.Paid);
                billDao.update(bill2);
                System.out.println("Thanh toan thanh cong");
            } else {
                System.out.println("So tien ban thanh toan con thieu: " + (total - total2));
            }

            paymentDao.insert(payment);

        }
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

    // Search all Bills with a specific bill_date

    private static List<Bill> searchBillByDate(LocalDate date) {
        return billDao.findAll().stream().filter(z -> z.getDate().equals(date)).collect(Collectors.toList());
    }
    // o Find all Payment belong a specific Bill

    private static List<Payment> searchPaymentByBill(int id) {
        return paymentDao.findAll().stream().filter(z -> z.getBill().getId() == id).collect(Collectors.toList());
    }

    // • Create a method to proceed paging operation for Patient use Stored
    // Procedure.
    public static List<Patient> getPagedPatients(int startRowIndex, int pageSize) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        ProcedureCall procedureCall = session.createStoredProcedureCall("getPagedPatients", Patient.class);

        // Đăng ký các tham số
        procedureCall.registerParameter("startRowIndex", Integer.class, ParameterMode.IN)
                .bindValue(startRowIndex);
        procedureCall.registerParameter("pageSize", Integer.class, ParameterMode.IN)
                .bindValue(pageSize);
        // Thực thi stored procedure và lấy kết quả
        ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
        ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
        @SuppressWarnings("unchecked")
        List<Patient> patients = resultSetOutput.getResultList();

        return patients;
    }

    public static List<Bill> pagingBill(int pageNumber, int pageSize) {
        List<Bill> bills = null;

        Session session = null;
        Transaction transaction = null;

        try {
            logger.info("Open session");
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Bill> criteriaQuery = criteriaBuilder.createQuery(Bill.class);

            Root<Bill> root = criteriaQuery.from(Bill.class);

            criteriaQuery.select(root);

            Query<Bill> query = session.createQuery(criteriaQuery);

            // ignore
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            bills = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(ErrorMessages.DATABASE_CONNECTION_ERROR);
            assert transaction != null;
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return bills;
    }

}
