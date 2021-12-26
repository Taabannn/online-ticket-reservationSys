package ir.maktab58.BusTicketBooking.view;

import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.service.BusTicketBookingService;

import java.util.*;

/**
 * @author Taban Soleymani
 */
public class BusTicketBookingSys {
    private final Scanner scanner = new Scanner(System.in);
    private final BusTicketBookingService bookingService = new BusTicketBookingService();
    private List listOfFilteredRecords;

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
        System.out.println("Please enter source, destination, num of records and date of travel(optional): ");
        String inputLine = scanner.nextLine().trim();
        String[] tokens = inputLine.split(" ");
        String source = tokens[0];
        String destination = tokens[1];
        int numOfRecords = Integer.parseInt(tokens[2]);
        if (tokens.length > 3) {
            int year = Integer.parseInt(tokens[3]);
            int month = Integer.parseInt(tokens[4]);
            int day = Integer.parseInt(tokens[5]);
            Date date = new Date(year - 1900, month - 1, day);
            showUserMenu(numOfRecords, source, destination, date, userId);
        } else {
            showUserMenu(numOfRecords, source, destination, userId);
        }
    }

    private void showUserMenu(int numOfRecords, String source, String destination, long userId) {
        int offset = 0;
        boolean exit = true;
        boolean filter = false;
        String filterOptions = "";
        while (!exit) {
            List listOfRecords;
            if (!filter) {
                listOfRecords = getListOfRecords(numOfRecords, source, destination, offset);
            } else {
                listOfRecords = listOfFilteredRecords;
                showListOfFilteredRecords(numOfRecords, offset);
            }
            System.out.println("""
                    1) previous page
                    2) next page
                    3) filter
                    4) reserve ticket
                    5) exit""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> offset = evaluateOffset(offset, numOfRecords);
                    case "2" -> offset = evaluateOffset(offset, numOfRecords, listOfRecords.size());
                    case "3" -> {
                        filter = true;
                        listOfFilteredRecords = getListOfFilteredRecords(source, destination, offset);
                    }
                    case "4" -> reserveTicket(listOfRecords, offset, userId);
                    case "5" -> exit = true;
                    default -> throw BookingSysEx.builder()
                            .message("Your choice must be an integer between 1 to 4.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void reserveTicket(List listOfRecords, int offset, long userId) {
        System.out.println("please enter the id of ticket you want to reserve");
        String idStr = scanner.nextLine().trim();
        int id = Integer.parseInt(idStr);
        if (id < offset || id > (offset + listOfRecords.size()))
            throw BookingSysEx.builder()
                    .message("The id doesn't exist in the list of records.")
                    .errorCode(400).build();
        System.out.println("how many passengers do you want to get ticket for?");
        int numOfPassengers = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < numOfPassengers; i++) {
            System.out.println("please enter passenger: firstname, lastname, national-code, phone-number, age and gender");
            String inputLine = scanner.nextLine().trim();
            bookingService.registerNewTicket(listOfRecords.get(id - offset), inputLine, userId);
        }
    }

    private void showListOfFilteredRecords(int numOfRecords, int offset) {
        System.out.println("list of records");
        if (listOfFilteredRecords.size() == 0 && offset != 1) {
            System.out.println("There is nothing to show.");
        } else {
            Map<Integer, Object> map = new HashMap<>();
            for (Object element : listOfFilteredRecords) {
                map.put(offset + listOfFilteredRecords.indexOf(element), element);
            }
            map.forEach((key, value) -> System.out.println(key + " " + value));
        }
    }

    private List getListOfFilteredRecords(String source, String destination, int offset) {
        System.out.println("""
                You can filter records based on:
                1) company name
                2) bus type
                3) price range 
                4) range of departure hour
                """);
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1" -> {
                System.out.println("please enter company name");
                String companyName = scanner.nextLine().trim();
                return bookingService.getListOfFilteredInfoByCompanyName(source, destination, offset, companyName);
            }
            case "2" -> {
                System.out.println("please enter bus type");
                String busType = scanner.nextLine().trim();
                return bookingService.getListOfFilteredInfoByBusType(source, destination, offset, busType);
            }
            case "3" -> {
                System.out.println("please enter price range");
                String priceRange = scanner.nextLine().trim();
                return bookingService.getListOfFilteredInfoByPriceRange(source, destination, offset, priceRange);
            }
            case "4" -> {
                System.out.println("please enter departure hour range");
                String departureHourRange = scanner.nextLine().trim();
                return bookingService.getListOfFilteredInfoByDepartureHourRange(source, destination, offset, departureHourRange);
            }
            default -> throw BookingSysEx.builder()
                    .message("Your choice must be an integer between 1 to 4.")
                    .errorCode(400).build();
        }
    }

    private List getListOfRecords(int numOfRecords, String source, String destination, int offset) {
        List listOfRecords;
        System.out.println("list of records");
        listOfRecords = bookingService.getListOfBookingInfo(offset, numOfRecords, source, destination);
        if (listOfRecords.size() == 0 && offset != 1) {
            System.out.println("There is nothing to show.");
        } else {
            Map<Integer, Object> map = new HashMap<>();
            for (Object element : listOfRecords) {
                map.put(offset + listOfRecords.indexOf(element), element);
            }
            map.forEach((key, value) -> System.out.println(key + " " + value));
        }
        return listOfRecords;
    }

    private void showUserMenu(int numOfRecords, String source, String destination, Date date, long userId) {
        int offset = 0;
        boolean exit = true;
        boolean filter = false;
        String filterOptions = "";
        while (!exit) {
            if (!filter) {
                System.out.println("list of records");
                listOfAllRecords.forEach(System.out::println);
            }

            System.out.println("""
                    1) previous page
                    2) next page
                    3) filter
                    4) exit""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> offset = evaluateOffset(offset, numOfRecords);
                    case "2" -> offset = evaluateOffset(offset, numOfRecords, listOfAllRecords.size());
                    case "3" -> {
                        filter = true;
                        filterOptions = getUserFilterOptions(filterOptions);
                    }
                    case "4" -> exit = true;
                    default -> throw BookingSysEx.builder()
                            .message("Your choice must be an integer between 1 to 4.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int evaluateOffset(int offset, int numOfRecords) {
        if (offset - numOfRecords < 0) {
            throw BookingSysEx.builder()
                    .message("offset can not be negative!")
                    .errorCode(400).build();
        }
        return (offset - numOfRecords);
    }

    private int evaluateOffset(int offset, int numOfRecords, int sizeOfRecords) {
        if (sizeOfRecords < numOfRecords) {
            throw BookingSysEx.builder()
                    .message("There is no more information to show.")
                    .errorCode(400).build();
        }
        return (offset + numOfRecords);
    }
}
