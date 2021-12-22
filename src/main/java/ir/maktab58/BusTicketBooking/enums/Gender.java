package ir.maktab58.BusTicketBooking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Taban Soleymani
 */
@NoArgsConstructor
@AllArgsConstructor
public enum Gender {
    MALE("male"), FEMALE("female");

    private @Getter String gender;
}
