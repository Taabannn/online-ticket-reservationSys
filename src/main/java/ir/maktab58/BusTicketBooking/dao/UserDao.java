package ir.maktab58.BusTicketBooking.dao;

import ir.maktab58.BusTicketBooking.entity.User;
import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * @author Taban Soleymani
 */
public class UserDao extends BaseDaoImpl<User> {

    public User findUserByUserAndPass(String username, String password) {
        User user;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User u where u.username=:username and u.password=:password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            user = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            throw BookingSysEx.builder()
                    .message("No user with entered username and password was found.")
                    .errorCode(400).build();
        }
        return user;
    }
}
