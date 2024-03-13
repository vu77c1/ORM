package la101.entities;

import java.time.LocalDate;

import javax.persistence.*;

import la101.enums.PaymentMethoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    @Column(name = "payment_date")
    private LocalDate date;

    @Column(name = "payment_method")
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethoad method;

    @Column(name = "payment_amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "fk_constraint_patient_patient"))
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", foreignKey = @ForeignKey(name = "fk_constraint_bill_bill"))
    private Bill bill;

    @Override
    public String toString() {
        return "Payment [id=" + id + ", date=" + date + ", method=" + method + ", amount=" + amount + ", patient="
                + patient + ", bill=" + bill + "]";
    }

}
