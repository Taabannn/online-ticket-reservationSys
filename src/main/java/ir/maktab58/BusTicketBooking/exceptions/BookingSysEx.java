package ir.maktab58.BusTicketBooking.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class BookingSysEx extends RuntimeException {
    private int errorCode;

    @Builder
    public BookingSysEx(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
