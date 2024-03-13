package finals.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import finals.enums.GioiTinh;
import lombok.Data;

@Entity
@Data
@Table(name = "NHAN_VIEN")
public class NHAN_VIEN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "dien_thoai")
    private String dienThoai;
    @Column(name = "so_nam_kinh_nghiem")
    private int soNamKinhNghiem;

    @Column(name = "gioi_tinh")
    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @OneToMany(mappedBy = "nhanVien")
    private List<LICH_SU_CHAM_SOC> lichSuChamSocs;

    public NHAN_VIEN(String tenNhanVien, String diaChi, String dienThoai, int soNamKinhNghiem, GioiTinh gioiTinh) {
        this.tenNhanVien = tenNhanVien;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.soNamKinhNghiem = soNamKinhNghiem;
        this.gioiTinh = gioiTinh;
    }

    public NHAN_VIEN() {
    }

    @Override
    public String toString() {
        return "NHAN_VIEN [id=" + id + ", tenNhanVien=" + tenNhanVien + ", diaChi=" + diaChi + ", dienThoai="
                + dienThoai + ", soNamKinhNghiem=" + soNamKinhNghiem + ", gioiTinh=" + gioiTinh + "]";
    }

}
