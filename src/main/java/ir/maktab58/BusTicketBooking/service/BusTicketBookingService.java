package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.entity.User;

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
}
