package la101.entities;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")

// @NamedStoredProcedureQuery(name = "getPagedPatients", procedureName =
// "getPagedPatients", resultClasses = Patient.class, parameters = {
// @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name
// = "startRowIndex"),
// @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name
// = "pageSize")
// })
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;
    // first name
    @Column(name = "first_name")
    private String firstName;
    // last name
    @Column(name = "last_name")
    private String lastName;
    // address
    @Column(name = "address")
    private String address;
    // citiy
    @Column(name = "city")
    private String city;
    // state
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "patient")
    private List<Payment> payments;

    @Override
    public String toString() {
        return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
                + ", city=" + city + ", state=" + state + "]";
    }
}
