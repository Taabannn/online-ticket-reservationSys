package ir.maktab58.BusTicketBooking.view;

import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.service.BusTicketBookingService;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class BusTicketBookingSys {
    private final Scanner scanner = new Scanner(System.in);
    private final BusTicketBookingService bookingService = new BusTicketBookingService();

    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    *****Welcome*****
                    1) login as a user
                    2) login as an admin
                    3) exit""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> loginAsUser();
                    case "2" -> loginAsAdmin();
                    case "3" -> exit = true;
                    default -> throw BookingSysEx.builder()
                            .message("Your choice must be an integer between 1 to 3.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void loginAsAdmin() {

    }

    private void loginAsUser() {

    }
}
