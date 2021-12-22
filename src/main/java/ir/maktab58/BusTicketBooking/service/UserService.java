package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.UserDao;
import ir.maktab58.BusTicketBooking.entity.User;

/**
 * @author Taban Soleymani
 */
public class UserService {
    private final UserDao userDao= new UserDao();

    public User checkIfUserExisted(String username, String password) {
        return userDao.findUserByUserAndPass(username, password);
    }
}
