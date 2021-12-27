package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.entity.BookingInfo;
import ir.maktab58.BusTicketBooking.entity.BusTicket;
import ir.maktab58.BusTicketBooking.entity.Passenger;
import ir.maktab58.BusTicketBooking.entity.User;
import ir.maktab58.BusTicketBooking.enums.BusType;
import ir.maktab58.BusTicketBooking.enums.Gender;

import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BusTicketBookingService {
    private final AdminService adminService = new AdminService();
    private final BookingInfoService bookingInfoService = new BookingInfoService();
    private final BusTicketService busTicketService = new BusTicketService();
    private final PassengerService passengerService = new PassengerService();
    private final UserService userService = new UserService();

    public boolean isAminExisted(String username, String password) {
        adminService.checkIfThisAdminExisted(username, password);
        return true;
    }


    public long isUserExisted(String username, String password) {
        User user = userService.checkIfUserExisted(username, password);
        return user.getId();
    }

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination, Date date) {
        return bookingInfoService.getListOfBookingInfo(start, numOfRecords, source, destination, date);
    }

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination) {
        return bookingInfoService.getListOfBookingInfo(start, numOfRecords, source, destination);
    }

    public List getListOfFilteredInfoByCompanyName(String source, String destination, int offset, String companyName) {
        return bookingInfoService.getListOfFilteredInfoByCompanyName(source, destination, offset, companyName);
    }

    public List getListOfFilteredInfoByBusType(String source, String destination, int offset, String busType) {
        BusType busTypeEnum = BusType.valueOf(busType);
        return bookingInfoService.getListOfFilteredInfoByBusType(source, destination, offset, busTypeEnum);
    }

    public List getListOfFilteredInfoByPriceRange(String source, String destination, int offset, String priceRange) {
        String[] priceRanges = priceRange.split(" ");
        long downBound = Long.parseLong(priceRanges[0]);
        long upBound = Long.parseLong(priceRanges[1]);
        return bookingInfoService.getListOfFilteredInfoByPriceRange(source, destination, offset, upBound, downBound);
    }

    public List getListOfFilteredInfoByDepartureHourRange(String source, String destination, int offset, String departureHourRange) {
        String[] departureHourRanges = departureHourRange.split(" ");
        int downBound = Integer.parseInt(departureHourRanges[0]);
        int upBound = Integer.parseInt(departureHourRanges[1]);
        return bookingInfoService.getListOfFilteredInfoByDepartureHourRange(source, destination, offset, upBound, downBound);
    }

    public void registerNewTicket(Object o, String inputLine, long userId) {
        if (o instanceof BookingInfoDTO) {
            BookingInfoDTO bookingInfoDTO = (BookingInfoDTO) o;
            String[] tokens = inputLine.split(" ");
            String name = tokens[0];
            String family = tokens[1];
            String nationalCode = tokens[2];
            String phoneNumber = tokens[3];
            int age = Integer.parseInt(tokens[4]);
            Gender gender = Gender.valueOf(tokens[5]);
            Passenger passenger = Passenger.builder()
                    .withFirstName(name).withLastName(family)
                    .withNationalCode(nationalCode).withPhoneNumber(phoneNumber)
                    .withAge(age).withGender(gender).build();
            BookingInfo bookingInfo = bookingInfoService.getBookingInfo(bookingInfoDTO);
            BusTicket busTicket = BusTicket.builder()
                    .withSource(bookingInfo.getSource())
                    .withDestination(bookingInfo.getDestination())
                    .withBus(bookingInfo.getBus())
                    .withDateOfTravel(bookingInfo.getDateOfTravel())
                    .withSeatNumber(bookingInfo.getCapacity() - bookingInfo.getNumOfRemainingSeat())
                    .withPassenger(passenger)
                    .withDepartureHour(bookingInfo.getDepartureHour())
                    .withCompanyName(bookingInfo.getCompanyName())
                    .withDateOfPurchase(new Date()).build();
            busTicketService.saveNewTicket(busTicket);
            bookingInfo.setNumOfRemainingSeat(bookingInfo.getNumOfRemainingSeat() - 1);
            bookingInfoService.updateNumberOfRemainingSeat(bookingInfo);
        }
    }
}
