package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.AdminDao;
import ir.maktab58.BusTicketBooking.entity.Admin;

/**
 * @author Taban Soleymani
 */
public class AdminService {
    private final AdminDao adminDao = new AdminDao();

    public void checkIfThisAdminExisted(String username, String password) {
        adminDao.findAdminByUserAndPass(username, password);
    }
}
