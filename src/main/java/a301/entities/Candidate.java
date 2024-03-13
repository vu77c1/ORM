package a301.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import a301.enums.Gender;
import lombok.Data;

@Data
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private int id;
    @NotEmpty(message = "full_name cannot be empty")
    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;
    @NotNull(message = "date_of_birth cannot be empty")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @NotNull(message = "graduation_year cannot be empty")
    @Column(name = "graduation_year", nullable = false)
    private LocalDate graduationYear;

    @NotEmpty(message = "phone cannot be empty")
    @Column(name = "phone", length = 255, unique = true)
    private String phone;

    @Email(message = "Invalid email address")
    @Column(name = "email", length = 255, unique = true)
    private String email;

    @Column(name = "skill", length = 255)
    private String skill;

    @Column(name = "foreign_language", length = 255)
    private String foreignLanguage;

    @Column(name = "level")
    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 7, message = "Level cannot exceed 7")
    private int level;
    @Column(name = "cv", length = 255)
    private String cv;

    @Column(name = "allocation_status")
    private int allocationStatus;

    @Column(name = "remark", length = 1000)
    private String remark;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EntryTest> entryTests;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Interview> interviews;

    @Override
    public String toString() {
        return "Candidate [id=" + id + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
                + ", graduationYear=" + graduationYear + ", phone=" + phone + ", email=" + email + ", skill=" + skill
                + ", foreignLanguage=" + foreignLanguage + ", level=" + level + ", cv=" + cv + ", allocationStatus="
                + allocationStatus + ", remark=" + remark + "]";
    }

}
