package slide.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "dbo", name = "jobs")
public class Jobs {
    @Id
    @Column(name = "job_id", length = 10)
    private String jobId;
    @Column(name = "job_title", length = 255, nullable = false, unique = true)

    private String jobTitle;
    @Column(name = "min_salary", precision = 11, scale = 2)

    private double minSalary;
    @Column(name = "max_salary", precision = 11, scale = 2)

    private double maxSalary;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobs")
    // private List<Employees> employees;

    public Jobs() {
    }

    public Jobs(String jobId, String jobTitle, double minSalary, double maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
}
