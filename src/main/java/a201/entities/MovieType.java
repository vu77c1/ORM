package a201.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "movie_type")
public class MovieType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_type_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private Type type;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private MoVie movie;

    @Column(name = "mt_description")
    private String description;

    @Override
    public String toString() {
        return "MovieType [id=" + id + ", type=" + type.getId() + ", movie=" + movie.getId() + ", description="
                + description + "]";
    }

}
