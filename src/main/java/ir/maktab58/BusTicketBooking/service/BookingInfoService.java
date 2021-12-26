package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.BookingInfoDao;

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
        return bookingInfoDao.getListOfFilteredBookingInfo(numOfRecords, source, destination, offset, companyName);
    }
}
