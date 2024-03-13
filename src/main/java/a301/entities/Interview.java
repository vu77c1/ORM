package a301.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "interview")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private int id;
    @Column(name = "interview_time")
    private String time;
    @Column(name = "interview_date")
    private LocalDate date;
    @Column(name = "interviewer")
    private String interviewer;
    @Column(name = "comments", columnDefinition = "varchar(2000)")
    private String comments;
    @Column(name = "interview_result")
    private String result;
    @Column(name = "remark", columnDefinition = "varchar(1000)")
    private String remark;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidate candidate;

}
