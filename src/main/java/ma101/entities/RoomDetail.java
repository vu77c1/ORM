package ma101.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "room_detail")
@NoArgsConstructor
@AllArgsConstructor

public class RoomDetail {

    @Id
    @Column(name = "room_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_rate")

    private int rate;

    @Column(name = "active_date")
    private LocalDate activeDate;

    @Column(name = "room_description", length = 250)
    private String description;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public String toString() {
        return "RoomDetail [id=" + id + ", rate=" + rate + ", activeDate=" + activeDate + ", description=" + description
                + ", room=" + room.getId() + "]";
    }

}
