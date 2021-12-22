package ir.maktab58.BusTicketBooking.entity;

import ir.maktab58.BusTicketBooking.enums.BusType;
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
public class BookingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String source;
    private String destination;
    @Temporal(TemporalType.DATE)
    private Date dateOfTravel;
    private int departureHour;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private String companyName;
}
