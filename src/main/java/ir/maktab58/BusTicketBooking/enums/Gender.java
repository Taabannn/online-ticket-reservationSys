package ir.maktab58.BusTicketBooking.enums;

import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
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

    public Gender getVal(String type) {
        if (type.equalsIgnoreCase("male"))
            return MALE;
        else if (type.equalsIgnoreCase("female"))
            return FEMALE;
        else
            throw BookingSysEx.builder()
                    .message("the entered type of gender is not accepted.")
                    .errorCode(400).build();
    }

    private @Getter String gender;
}
