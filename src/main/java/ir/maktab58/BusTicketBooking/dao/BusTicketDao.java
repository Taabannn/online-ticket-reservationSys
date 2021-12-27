package ir.maktab58.BusTicketBooking.dao;

import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.dto.ResultOfReservationDTO;
import ir.maktab58.BusTicketBooking.entity.BookingInfo;
import ir.maktab58.BusTicketBooking.entity.BusTicket;
import ir.maktab58.BusTicketBooking.utils.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BusTicketDao extends BaseDaoImpl<BusTicket> {
    public List getListOfBusTickets() {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BusTicket.class, "ticket");
        criteria.createCriteria("ticket.bus", "bus");

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("bus.plateNumber").as("plateNumber"))
                .add(Projections.property("ticket.dateOfTravel").as("dateOfTravel"))
                .add(Projections.property("ticket.departureHour").as("departureHour"))
                .add(Projections.property("ticket.source").as("source"))
                .add(Projections.property("ticket.destination").as("destination"))
                .add(Projections.count("bus").as("numOfSoldTickets"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(ResultOfReservationDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }
}
