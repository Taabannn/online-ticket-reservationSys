package ir.maktab58.BusTicketBooking.dao;

import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.entity.BookingInfo;
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
public class BookingInfoDao extends BaseDaoImpl<BookingInfoDao> {
    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination, Date date) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion dateCon = Restrictions.eq("info.DateOfTravel", date);
        LogicalExpression sourceAndDes = Restrictions.and(sourceCon, desCon);
        criteria.add(Restrictions.and(sourceAndDes, dateCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.setFirstResult(start).setMaxResults(numOfRecords).list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        criteria.add(Restrictions.and(sourceCon, desCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.setFirstResult(start).setMaxResults(numOfRecords).list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfo(int numOfRecords, String source, String destination, int offset, String companyName) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion companyCon = Restrictions.eq("info.companyName", companyName);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        criteria.add(Restrictions.and(andResult, companyCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.setFirstResult(offset).setMaxResults(numOfRecords).list();

        transaction.commit();
        session.close();
        return list;
    }
}
