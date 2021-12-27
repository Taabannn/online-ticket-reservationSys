package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.BusTicketDao;
import ir.maktab58.BusTicketBooking.entity.BusTicket;

/**
 * @author Taban Soleymani
 */
public class BusTicketService {
    private final BusTicketDao busTicketDao = new BusTicketDao();

    public void saveNewTicket(BusTicket busTicket) {
        busTicketDao.save(busTicket);
    }
}
