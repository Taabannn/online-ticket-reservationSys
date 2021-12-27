package ir.maktab58.BusTicketBooking.entity;

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
    private int numOfRemainingSeat;
    @OneToOne(cascade = CascadeType.ALL)
    private Bus bus;
    private String companyName;
    private long price;
}
