package ir.maktab58.BusTicketBooking.dao;

import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.entity.BookingInfo;
import ir.maktab58.BusTicketBooking.entity.User;
import ir.maktab58.BusTicketBooking.enums.BusType;
import ir.maktab58.BusTicketBooking.exceptions.BookingSysEx;
import ir.maktab58.BusTicketBooking.utils.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BookingInfoDao extends BaseDaoImpl<BookingInfo> {
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

    public List getListOfFilteredBookingInfoByCompanyName(String source, String destination, int offset, String companyName) {
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
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByCompanyName(String source, String destination, int offset, String companyName, Date date) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion companyCon = Restrictions.eq("info.companyName", companyName);
        Criterion dateCon = Restrictions.eq("info.dateOfTravel", date);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression andResult1 = Restrictions.and(companyCon, dateCon);
        criteria.add(Restrictions.and(andResult, andResult1));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }


    public List getListOfFilteredBookingInfoByBusType(String source, String destination, int offset, BusType busTypeEnum) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion busTypeCon = Restrictions.eq("bus.busType", busTypeEnum);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        criteria.add(Restrictions.and(andResult, busTypeCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByBusType(String source, String destination, int offset, BusType busTypeEnum, Date date) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion busTypeCon = Restrictions.eq("bus.busType", busTypeEnum);
        Criterion dateCon = Restrictions.eq("info.dateOfTravel", date);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression andResult1 = Restrictions.and(busTypeCon, dateCon);
        criteria.add(Restrictions.and(andResult, andResult1));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByPriceRange(String source, String destination, int offset, long upBound, long downBound) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion upBoundCon = Restrictions.lt("info.price", upBound);
        Criterion downBoundCon = Restrictions.gt("info.price", downBound);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression boundaryResult = Restrictions.and(upBoundCon, downBoundCon);
        criteria.add(Restrictions.and(andResult, boundaryResult));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByPriceRange(String source, String destination, int offset, long upBound, long downBound, Date date) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion upBoundCon = Restrictions.lt("info.price", upBound);
        Criterion downBoundCon = Restrictions.gt("info.price", downBound);
        Criterion dateCon = Restrictions.eq("info.dateOfTravel", date);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression boundaryResult = Restrictions.and(upBoundCon, downBoundCon);
        andResult = Restrictions.and(boundaryResult, andResult);
        criteria.add(Restrictions.and(andResult, dateCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByDepartureHourRange(String source, String destination, int offset, int upBound, int downBound) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion upBoundCon = Restrictions.lt("info.price", upBound);
        Criterion downBoundCon = Restrictions.gt("info.price", downBound);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression boundaryResult = Restrictions.and(upBoundCon, downBoundCon);
        criteria.add(Restrictions.and(andResult, boundaryResult));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List getListOfFilteredBookingInfoByDepartureHourRange(String source, String destination, int offset, int upBound, int downBound, Date date) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(BookingInfo.class, "info");
        criteria.createCriteria("info.bus", "bus");

        Criterion sourceCon = Restrictions.eq("info.source", source);
        Criterion desCon = Restrictions.eq("info.destination", destination);
        Criterion upBoundCon = Restrictions.lt("info.price", upBound);
        Criterion downBoundCon = Restrictions.gt("info.price", downBound);
        Criterion dateCon = Restrictions.eq("info.dateOfTravel", date);
        LogicalExpression andResult = Restrictions.and(sourceCon, desCon);
        LogicalExpression boundaryResult = Restrictions.and(upBoundCon, downBoundCon);
        andResult = Restrictions.and(andResult, boundaryResult);
        criteria.add(Restrictions.and(andResult, dateCon));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("info.companyName").as("companyName"))
                .add(Projections.property("bus.busType").as("busType"))
                .add(Projections.property("info.departureHour").as("departureHour"))
                .add(Projections.property("info.numOfRemainingSeat").as("numOfRemainingSeat"))
                .add(Projections.property("info.price").as("price"))
        );

        criteria.setResultTransformer(Transformers.aliasToBean(BookingInfoDTO.class));
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public BookingInfo findBookingInfoByDTODetails(BookingInfoDTO bookingInfoDTO) {
        BookingInfo bookingInfo;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<BookingInfo> query = session.createQuery("from BookingInfo b where b.bus.busType=:busType and b.companyName=:companyName and b.departureHour=:departureHour and b.numOfRemainingSeat=:numOfRemainingSeats and b.price=:price", BookingInfo.class)
                    .setParameter("busType", bookingInfoDTO.getBusType())
                    .setParameter("companyName", bookingInfoDTO.getCompanyName())
                    .setParameter("departureHour", bookingInfoDTO.getDepartureHour())
                    .setParameter("numOfRemainingSeats", bookingInfoDTO.getNumOfRemainingSeat())
                    .setParameter("price", bookingInfoDTO.getPrice());
            bookingInfo = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            throw BookingSysEx.builder()
                    .message("No bookingInfo with entered details was found.")
                    .errorCode(400).build();
        }
        return bookingInfo;
    }

    public void updateNumberOfRemainingSeats(BookingInfo bookingInfo) {
        update(bookingInfo);
    }
}
