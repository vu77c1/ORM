package finals.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import finals.enums.GioiTinh;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "KHACH_HANG")
public class KHACH_HANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "dien_thoai")
    private String dienThoai;
    @Column(name = "gioi_tinh")
    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    public KHACH_HANG(String tenKhachHang, String diaChi, String dienThoai, GioiTinh gioiTinh) {
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.gioiTinh = gioiTinh;
    }

    public KHACH_HANG() {
    }

    @ManyToOne
    @JoinColumn(name = "loai_khach_hang_id")
    private LOAI_KHACH_HANG loaiKhachHang;

    @OneToMany(mappedBy = "khachHang")
    private List<LICH_SU_CHAM_SOC> lichSuChamSocs;

    @Override
    public String toString() {
        return "KHACH_HANG [id=" + id + ", tenKhachHang=" + tenKhachHang + ", diaChi=" + diaChi + ", dienThoai="
                + dienThoai + ", gioiTinh=" + gioiTinh + "]";
    }

}
