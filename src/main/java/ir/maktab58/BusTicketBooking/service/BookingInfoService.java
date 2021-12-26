package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.BookingInfoDao;
import ir.maktab58.BusTicketBooking.enums.BusType;

import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BookingInfoService {
    private final BookingInfoDao bookingInfoDao = new BookingInfoDao();

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination, Date date) {
        return bookingInfoDao.getListOfBookingInfo(start, numOfRecords, source, destination, date);
    }

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination) {
        return bookingInfoDao.getListOfBookingInfo(start, numOfRecords, source, destination);
    }

    public List getListOfFilteredInfoByCompanyName(int numOfRecords, String source, String destination, int offset, String companyName) {
        return bookingInfoDao.getListOfFilteredBookingInfoByCompanyName(numOfRecords, source, destination, offset, companyName);
    }

    public List getListOfFilteredInfoByBusType(int numOfRecords, String source, String destination, int offset, BusType busTypeEnum) {
        return bookingInfoDao.getListOfFilteredBookingInfoByBusType(numOfRecords, source, destination, offset, busTypeEnum);
    }

    public List getListOfFilteredInfoByPriceRange(int numOfRecords, String source, String destination, int offset, long upBound, long downBound) {
        return bookingInfoDao.getListOfFilteredBookingInfoByPriceRange(numOfRecords, source, destination, offset, upBound, downBound);
    }
}
