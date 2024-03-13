package finals.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "LOAI_KHACH_HANG")
public class LOAI_KHACH_HANG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "mo_ta")
    private String mo_ta;

    @OneToMany(mappedBy = "loaiKhachHang")
    private List<KHACH_HANG> khachHangs;

    public LOAI_KHACH_HANG(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public LOAI_KHACH_HANG() {
    }

}
