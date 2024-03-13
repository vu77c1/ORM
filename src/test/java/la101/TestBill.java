package la101;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import la101.dao.BillDao;
import la101.entities.Appointment;
import la101.entities.Bill;
import la101.enums.BillStatus;
import la101.ipl.BillDaoipl;

public class TestBill {
    static BillDao billDao;

    @BeforeAll
    public static void init() {
        billDao = new BillDaoipl();
    }

    @Test
    public void testSave() {
        Bill bill = new Bill();
        bill.setDate(LocalDate.now());
        bill.setStatus(BillStatus.Unpaid);
        bill.setTotal(200000.0);
        Appointment appointment = new Appointment();
        appointment.setId(5);
        bill.setAppointment(appointment);
        billDao.save(bill);
        assertNotNull(bill.getId());
    }

    @Test
    public void testFindById() {
        Bill bill = billDao.findById(1);
        assertNotNull(bill);
        System.out.println(bill);
    }

    @Test
    public void testDelete() {
        Bill bill = billDao.findById(1);
        billDao.delete(bill);
    }

    @Test
    public void testUpdate() {
        Bill bill = billDao.findById(2);
        bill.setStatus(BillStatus.Paid);
        billDao.update(bill);
    }

    @Test
    public void testInsert() {
        Bill bill = new Bill();
        bill.setDate(LocalDate.now());
        bill.setStatus(BillStatus.Unpaid);
        bill.setTotal(200000.0);
        Appointment appointment = new Appointment();
        appointment.setId(5);
        bill.setAppointment(appointment);
        billDao.insert(bill);
        assertNotNull(bill.getId());
    }

    @Test
    public void testFindAll() {
        List<Bill> bills = billDao.findAll();
        assertNotNull(bills);
        assertFalse(bills.isEmpty());
        bills.forEach(bill -> System.out.println(bill));
    }

}
