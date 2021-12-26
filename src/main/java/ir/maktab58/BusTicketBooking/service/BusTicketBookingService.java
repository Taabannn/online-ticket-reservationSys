package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.entity.User;
import ir.maktab58.BusTicketBooking.enums.BusType;

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

    public List getListOfFilteredInfoByCompanyName(int numOfRecords, String source, String destination, int offset, String companyName) {
        return bookingInfoService.getListOfFilteredInfoByCompanyName(numOfRecords, source, destination, offset, companyName);
    }

    public List getListOfFilteredInfoByBusType(int numOfRecords, String source, String destination, int offset, String busType) {
        BusType busTypeEnum = BusType.valueOf(busType);
        return bookingInfoService.getListOfFilteredInfoByBusType(numOfRecords, source, destination, offset, busTypeEnum);
    }

    public List getListOfFilteredInfoByPriceRange(int numOfRecords, String source, String destination, int offset, String priceRange) {
        String[] priceRanges = priceRange.split(" ");
        long downBound = Long.parseLong(priceRanges[0]);
        long upBound = Long.parseLong(priceRanges[1]);
        return bookingInfoService.getListOfFilteredInfoByPriceRange(numOfRecords, source, destination, offset, upBound, downBound);
    }
}
