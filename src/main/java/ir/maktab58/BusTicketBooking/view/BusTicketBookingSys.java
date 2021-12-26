package ir.maktab58.BusTicketBooking.view;

import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.service.BusTicketBookingService;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        bookingService.isAminExisted(username, password);
        showResultToAdmin();
    }

    private void showResultToAdmin() {

    }

    private void loginAsUser() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        long userId = bookingService.isUserExisted(username, password);
        showUserMenu(userId);
    }

    private void showUserMenu(long userId) {
        int start = 0;
        System.out.println("Please enter source, destination, num of records and date of travel(optional): ");
        String inputLine = scanner.nextLine().trim();
        List listOfAllRecords;
        String[] tokens = inputLine.split(" ");
        String source = tokens[0];
        String destination = tokens[1];
        int numOfRecords = Integer.parseInt(tokens[2]);
        if (tokens.length > 3) {
            int year = Integer.parseInt(tokens[3]);
            int month = Integer.parseInt(tokens[4]);
            int day = Integer.parseInt(tokens[5]);
            Date date = new Date(year - 1900, month + 1, day);
            listOfAllRecords = bookingService.getListOfBookingInfo(start, numOfRecords, source, destination, date);
        } else {
            listOfAllRecords = bookingService.getListOfBookingInfo(start, numOfRecords, source, destination);
        }
        boolean exit = true;
        while (true) {
            System.out.println("list of records");
            listOfAllRecords.forEach(System.out::println);

        }
    }
}
