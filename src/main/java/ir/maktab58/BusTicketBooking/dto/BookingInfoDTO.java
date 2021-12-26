package ir.maktab58.BusTicketBooking.dto;

import ir.maktab58.BusTicketBooking.enums.BusType;
import lombok.*;

/**
 * @author Taban Soleymani
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BookingInfoDTO {
    private String companyName;
    private BusType busType;
    private int departureHour;
    private long price;
    private int numOfRemainingSeat;
}
