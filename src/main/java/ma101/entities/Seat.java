package ma101.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma101.enums.SeatStatus;
import ma101.enums.SeatType;

@Entity
@Data
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor

public class Seat {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seat_column", length = 255)
    private String column;

    @Column(name = "seat_row")
    private int row;

    @Column(name = "seat_status", columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", columnDefinition = "varchar(255)")
    private SeatType type;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Seat(int id, String column, int row, SeatStatus status, SeatType type) {
        this.id = id;
        this.column = column;
        this.row = row;
        this.status = status;
        this.type = type;
    }

}
