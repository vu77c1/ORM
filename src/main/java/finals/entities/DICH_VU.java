package finals.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "DICH_VU")
public class DICH_VU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_dich_vu")
    private String tenDichVu;
    @Column(name = "don_gia")
    private Double donGia;

    @OneToMany(mappedBy = "dichVu")
    private List<LICH_SU_CHAM_SOC> lichSuChamSocs;

    public DICH_VU(String tenDichVu, Double donGia) {
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

    public DICH_VU() {
    }

    @Override
    public String toString() {
        return "DICH_VU [id=" + id + ", tenDichVu=" + tenDichVu + ", donGia=" + donGia + "]";
    }

}
