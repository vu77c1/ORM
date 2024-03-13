package slide.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity

public class Employees {

    @Id
    @Column(name = "employee_id")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne()
    @JoinColumn(name = "job_id", columnDefinition = "job_id", referencedColumnName = "job_id")
    private Jobs jobs;

}
