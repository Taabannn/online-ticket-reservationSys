package ir.maktab58.BusTicketBooking.dto;

import ir.maktab58.BusTicketBooking.entity.Bus;
import ir.maktab58.BusTicketBooking.enums.BusType;
import lombok.*;

import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResultOfReservationDTO {
    private BusType busType;
    private String plateNumber;
    private int departureHour;
    private String source;
    private String destination;
    private int numOfSoldTickets;
    private int numOfRemainingSeats;
    private Date dateOfTravel;
}
