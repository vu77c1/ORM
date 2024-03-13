package finals.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "LICH_SU_CHAM_SOC")
public class LICH_SU_CHAM_SOC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ngay_cham_soc")
    private LocalDate ngayChamSoc;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "tong_tien")
    private double tongTien;
    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NHAN_VIEN nhanVien;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KHACH_HANG khachHang;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dich_vu_id")
    private DICH_VU dichVu;

    public LICH_SU_CHAM_SOC(LocalDate ngayChamSoc, String trangThai, double tongTien) {
        this.ngayChamSoc = ngayChamSoc;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public LICH_SU_CHAM_SOC() {
    }

    @Override
    public String toString() {
        return "LICH_SU_CHAM_SOC{" +
                "id=" + id +
                ", ngayChamSoc=" + ngayChamSoc +
                ", trangThai='" + trangThai + '\'' +
                ", tongTien=" + tongTien +
                ", nhanVien=" + nhanVien +
                ", khachHang=" + khachHang +
                ", dichVu=" + dichVu +
                '}';
    }

}
