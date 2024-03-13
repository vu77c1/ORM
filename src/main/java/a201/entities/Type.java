package a201.entities;

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
@Table(name = "type")
public class Type {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_name", unique = true)
    private String name;
    @Column(name = "type_description")
    private String description;
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private List<MovieType> movieTypes;

    @Override
    public String toString() {
        return "Type [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
