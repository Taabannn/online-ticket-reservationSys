package ir.maktab58.BusTicketBooking.dao;

import ir.maktab58.BusTicketBooking.entity.Admin;
import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * @author Taban Soleymani
 */
public class AdminDao extends BaseDaoImpl<Admin> {

    public Admin findAdminByUserAndPass(String username, String password) {
        Admin admin;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Admin> query = session.createQuery("from Admin a where a.username=:username and a.password=:password", Admin.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            admin = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            throw BookingSysEx.builder()
                    .message("No admin with entered username and password was found.")
                    .errorCode(400).build();
        }
        return admin;
    }
}
