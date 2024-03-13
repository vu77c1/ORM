package finals.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.hibernate.Session;
import finals.dao.DichVuDao;
import finals.dao.KhachHangDao;
import finals.dao.LSCSDao;
import finals.dao.LoaiKhachHangDao;
import finals.dao.NhanVienDao;
import finals.entities.DICH_VU;
import finals.entities.KHACH_HANG;
import finals.entities.LICH_SU_CHAM_SOC;
import finals.entities.LOAI_KHACH_HANG;
import finals.entities.NHAN_VIEN;
import finals.enums.GioiTinh;
import finals.ipl.DichVuDaoIpl;
import finals.ipl.KhachHangDaoIpl;
import finals.ipl.LSCSDaoIpl;
import finals.ipl.LoaiKhachHangIpl;
import finals.ipl.NhanVienDaoIpl;
import finals.utils.HibernateUtils;

public class app {
    static LoaiKhachHangDao loaiKhachHangDao = new LoaiKhachHangIpl();
    static KhachHangDao khachHangDao = new KhachHangDaoIpl();
    static DichVuDao dichVuDao = new DichVuDaoIpl();
    static NhanVienDao nhanVienDao = new NhanVienDaoIpl();
    static LSCSDao lscsDao = new LSCSDaoIpl();

    public static void main(String[] args) {
        // connectDb();

        //

        Boolean insert = false;
        if (khachHangDao.findAll().isEmpty()) {
            insert = true;

        }
        if (insert) {
            insertLoaiKhachHang();
            insertKhachHang();
            InsertNhanVien();
            InsertDichDu();
            System.out.println("Inserted successfully");

        }
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("MENU:");
            System.out.println("1. Hien thi thong tin khach hang");
            System.out.println("2. Phan cong cham soc khach hang");
            System.out.println("3. Hoan tat va thanh toan");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (lscsDao.findAll().isEmpty()) {
                        insertLichSuChamSoc();
                    } else {
                        List<KHACH_HANG> khachHangs = khachHangDao.findByLichSuChamSoc();
                        if (!khachHangs.isEmpty()) {
                            khachHangs.forEach(System.out::println);

                        } else {
                            System.out.println("hiện tại không có khách hàng nào cần chăm sóc");

                        }

                    }

                    break;
                case 2:
                    System.out.println("DANH SACH NHAN VIEN");
                    List<NHAN_VIEN> nhanViens = nhanVienDao.findAll();
                    nhanViens.forEach(System.out::println);
                    System.out.println("Danh sach khach hang can cham soc");
                    List<KHACH_HANG> khachHang = khachHangDao.findByLichSuChamSoc();
                    khachHang.forEach(System.out::println);
                    System.out.println("Nhan vien ID");
                    int id = scanner.nextInt();
                    System.out.println("Ban da chon nhan vien " + id);
                    NHAN_VIEN c = nhanVienDao.findById(id);
                    System.out.println(c);

                    System.out.println("Khach hang ID");
                    int idKH = scanner.nextInt();
                    System.out.println("Ban da chon khach hang " + idKH);
                    KHACH_HANG d = khachHangDao.findById(idKH);
                    System.out.println(d);
                    if (!c.getGioiTinh().equals(d.getGioiTinh())) {
                        System.out.println("2 nhan vien nay khac gioi tinh khong the cham soc");
                    } else {
                        System.out.println("Danh sach dich vu:");
                        List<DICH_VU> dichVus = dichVuDao.findAll();
                        dichVus.forEach(System.out::println);
                        System.out.println("Chon dich vu: ");
                        int idDV = scanner.nextInt();
                        DICH_VU dv = dichVuDao.findById(idDV);
                        System.out.println("Ban da chon dich vu " + idDV);
                        System.out.println(dv);
                        LICH_SU_CHAM_SOC ls = new LICH_SU_CHAM_SOC();
                        ls.setKhachHang(d);
                        ls.setNhanVien(c);
                        ls.setDichVu(dv);
                        ls.setNgayChamSoc(LocalDate.now());
                        ls.setTrangThai("DangChamSoc");
                        lscsDao.insert(ls);
                        System.out.println("Lich su cham soc da duoc them vao");
                    }

                    break;
                case 3:
                    System.out.println("Danh sach khach hang dang cham soc");
                    List<LICH_SU_CHAM_SOC> lscs = lscsDao.findAll().stream()
                            .filter(z -> z.getTrangThai().equals("DangChamSoc")).collect(Collectors.toList());
                    lscs.forEach(System.out::println);
                    System.out.println("Chon ma dich vu cua khach hang:");
                    int idKHTT = scanner.nextInt();
                    System.out.println("Ban da chon  " + idKHTT);
                    LICH_SU_CHAM_SOC lscs1 = lscsDao.findById(idKHTT);
                    System.out.println(lscs1);
                    double discount;
                    String type = khachHangDao.findById(lscs1.getKhachHang().getId()).getLoaiKhachHang()
                            .getMo_ta();

                    switch (type) {
                        case "VIP":
                            discount = 0.20;
                            break;
                        case "Gold":
                            discount = 0.10;
                            break;
                        case "Silver":
                            discount = 0.05;
                            break;
                        case "VangLai":
                            discount = 0.0;
                            break;
                        default:
                            System.out.println("Null.");
                            return;
                    }
                    double price = lscs1.getDichVu().getDonGia();
                    double total = price - (price * discount);
                    System.out.println(total);
                    lscs1.setTongTien(total);
                    lscs1.setTrangThai("DachamSoc");
                    lscsDao.update(lscs1);
                    // print Bill detail
                    System.out.println("Chi tiet hoa don");
                    System.out.println("Ten KH: " + lscs1.getKhachHang().getTenKhachHang());
                    System.out.println("Ten DV: " + lscs1.getDichVu().getTenDichVu());
                    System.out.println("Giam gia: " + discount);
                    System.out.println("Tong tien: " + lscs1.getTongTien());
                    System.out.println("Thanh toan thanh cong");

                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 4);

        scanner.close();

    }

    private static void insertLoaiKhachHang() {
        LOAI_KHACH_HANG kh1 = new LOAI_KHACH_HANG("VIP");
        LOAI_KHACH_HANG kh2 = new LOAI_KHACH_HANG("Gold");
        LOAI_KHACH_HANG kh3 = new LOAI_KHACH_HANG("Silver");
        LOAI_KHACH_HANG kh4 = new LOAI_KHACH_HANG("VangLai");
        loaiKhachHangDao.insert(kh1);
        loaiKhachHangDao.insert(kh2);
        loaiKhachHangDao.insert(kh3);
        loaiKhachHangDao.insert(kh4);

    }

    public static List<KHACH_HANG> searchKhachHang(String loai) {
        return khachHangDao.findAll().stream()
                .filter(z -> z.getLoaiKhachHang().getMo_ta().equals(loai))
                .toList();

    }

    private static void insertKhachHang() {
        KHACH_HANG kh1 = new KHACH_HANG("Nguyen Van A", "HCM", "123456789", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh1 = new LOAI_KHACH_HANG();
        lkh1.setId(1);
        kh1.setLoaiKhachHang(lkh1);
        khachHangDao.insert(kh1);

        KHACH_HANG kh2 = new KHACH_HANG("Nguyen Van B", "HCM", "123456788", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh2 = new LOAI_KHACH_HANG();
        lkh2.setId(1);
        kh2.setLoaiKhachHang(lkh2);
        khachHangDao.insert(kh2);

        KHACH_HANG kh3 = new KHACH_HANG("Nguyen Van C", "HCM", "123456787", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh3 = new LOAI_KHACH_HANG();
        lkh3.setId(2);
        kh3.setLoaiKhachHang(lkh3);
        khachHangDao.insert(kh3);

        KHACH_HANG kh4 = new KHACH_HANG("Nguyen Van D", "HCM", "123456786", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh4 = new LOAI_KHACH_HANG();
        lkh4.setId(2);
        kh4.setLoaiKhachHang(lkh4);
        khachHangDao.insert(kh4);

        KHACH_HANG kh5 = new KHACH_HANG("Nguyen Van E", "HCM", "123456785", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh5 = new LOAI_KHACH_HANG();
        lkh5.setId(3);
        kh5.setLoaiKhachHang(lkh5);
        khachHangDao.insert(kh5);

        KHACH_HANG kh6 = new KHACH_HANG("Nguyen Van F", "HCM", "123456784", GioiTinh.Nam);
        LOAI_KHACH_HANG lkh6 = new LOAI_KHACH_HANG();
        lkh6.setId(3);
        kh6.setLoaiKhachHang(lkh6);
        khachHangDao.insert(kh6);

        KHACH_HANG kh7 = new KHACH_HANG("Nguyen Thi G", "HCM", "123456783", GioiTinh.Nu);
        LOAI_KHACH_HANG lkh7 = new LOAI_KHACH_HANG();
        lkh7.setId(4);
        kh7.setLoaiKhachHang(lkh7);
        khachHangDao.insert(kh7);

        KHACH_HANG kh8 = new KHACH_HANG("Nguyen Thi H", "HCM", "123456782", GioiTinh.Nu);
        LOAI_KHACH_HANG lkh8 = new LOAI_KHACH_HANG();
        lkh8.setId(4);
        kh8.setLoaiKhachHang(lkh8);
        khachHangDao.insert(kh8);

    }

    private static void InsertDichDu() {
        DICH_VU dv = new DICH_VU("Cham soc da", 1000000.0);
        dichVuDao.insert(dv);

        DICH_VU dv1 = new DICH_VU("Lam trang da", 2000000.0);
        dichVuDao.insert(dv1);

        DICH_VU dv2 = new DICH_VU("Tay te bao chet", 3000000.0);
        dichVuDao.insert(dv2);

    }

    private static void InsertNhanVien() {
        NHAN_VIEN nv1 = new NHAN_VIEN("Nguyen Van A", "HCM", "0123456789", 5, GioiTinh.Nam);
        nhanVienDao.insert(nv1);

        NHAN_VIEN nv2 = new NHAN_VIEN("Nguyen Van B", "Quy Nhon", "0123456788", 4, GioiTinh.Nam);
        nhanVienDao.insert(nv2);

        NHAN_VIEN nv3 = new NHAN_VIEN("Nguyen Van C", "Ha Noi", "0123456787", 5, GioiTinh.Nam);
        nhanVienDao.insert(nv3);

        NHAN_VIEN nv4 = new NHAN_VIEN("Nguyen Thi D", "Can Tho", "0123456786", 2, GioiTinh.Nu);
        nhanVienDao.insert(nv4);

        NHAN_VIEN nv5 = new NHAN_VIEN("Nguyen Thi E", "Bac Lieu", "0123456785", 1, GioiTinh.Nu);
        nhanVienDao.insert(nv5);

        NHAN_VIEN nv6 = new NHAN_VIEN("Nguyen Van F", "Soc Trang", "0123456784", 3, GioiTinh.Nu);
        nhanVienDao.insert(nv6);

    }

    private static void insertLichSuChamSoc() {
        LICH_SU_CHAM_SOC lichSuChamSoc = new LICH_SU_CHAM_SOC(LocalDate.now(), "DaChamSoc", 1000000.0);
        NHAN_VIEN nhanVien = new NHAN_VIEN();
        nhanVien.setId(1);
        lichSuChamSoc.setNhanVien(nhanVien);

        KHACH_HANG khachHang = new KHACH_HANG();
        khachHang.setId(1);
        lichSuChamSoc.setKhachHang(khachHang);
        DICH_VU dichVu = new DICH_VU();
        dichVu.setId(1);
        lichSuChamSoc.setDichVu(dichVu);
        lscsDao.insert(lichSuChamSoc);

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
