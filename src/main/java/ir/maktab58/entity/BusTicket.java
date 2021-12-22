package ir.maktab58.entity;

import ir.maktab58.enums.BusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class BusTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String source;
    private String destination;
    @Temporal(TemporalType.DATE)
    private Date dateOfTravel;
    private int departureHour;
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private String companyName;
    @ManyToOne(cascade = CascadeType.ALL)
    Passenger passenger;
}
