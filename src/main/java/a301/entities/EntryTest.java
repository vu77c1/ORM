package a301.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EntryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int id;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "language_valuator")
    private String languageValuator;

    @Column(name = "language_result", columnDefinition = "decimal(4, 2)")
    private double languageResult;

    @Column(name = "technical_valuator")
    private String technicalValuator;

    @Column(name = "technical_result", columnDefinition = "decimal(4, 2)")
    private double technicalResult;

    @Column(name = "remark", columnDefinition = "varchar(1000)")
    private String remark;

    @Column(name = "result")
    private String result;

    @Column(name = "entry_test_skill")
    private String entryTestSkill;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidate candidate;

}
