package la101.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import la101.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int id;
    @Column(name = "bill_date")
    private LocalDate date;
    @Column(name = "bill_total")
    private Double total;
    @Column(name = "bill_status")
    @Enumerated(EnumType.ORDINAL)
    private BillStatus status;
    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    private List<Payment> payments;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Override
    public String toString() {
        return "Bill [id=" + id + ", date=" + date + ", total=" + total + ", status=" + status + "]";
    }

}
