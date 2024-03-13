package a201.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "movie")
public class MoVie {
    @Id

    @Column(name = "movie_id", columnDefinition = "varchar(10)")
    private String Id;
    @Column(name = "actor", columnDefinition = "varchar(255)")
    private String actor;
    @Column(name = "content", columnDefinition = "varchar(1000)")
    private String content;
    @Column(name = "director", columnDefinition = "varchar(255)")
    private String director;
    @Column(name = "duration", columnDefinition = "decimal(5,2)")
    private double duration;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Column(name = "movie_production_company", columnDefinition = "varchar(255)")
    private String movieProducttionCompany;
    @Column(name = "movie_version", columnDefinition = "varchar(255)")
    private String version;
    @Column(name = "movie_name_eng", unique = true, columnDefinition = "varchar(255)")
    private String movieNameENG;
    @Column(name = "movie_name_vn", unique = true, columnDefinition = "varchar(255)")
    private String movieNameVN;
    @Column(name = "large_image")
    private String largeImage;
    @Column(name = "small_image")
    private String smallImage;
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<MovieType> movieTypes;

    @Override
    public String toString() {
        return "MoVie [Id=" + Id + ", actor=" + actor + ", content=" + content + ", director=" + director
                + ", duration=" + duration + ", fromDate=" + fromDate + ", toDate=" + toDate
                + ", movieProducttionCompany=" + movieProducttionCompany + ", version=" + version
                + ", movieNameENG=" + movieNameENG + ", movieNameVN=" + movieNameVN + ", largeImage="
                + largeImage + ", smallImage=" + smallImage + ", movieTypes=" + movieTypes + "]";
    }

}
