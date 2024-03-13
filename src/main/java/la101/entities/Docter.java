package la101.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "docter")
public class Docter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "docter_id")
    private int id;

    @Column(name = "fist_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "docter", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    @Override
    public String toString() {
        return "Docter [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}
