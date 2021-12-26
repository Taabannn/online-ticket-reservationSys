package ir.maktab58.BusTicketBooking.service;

import ir.maktab58.BusTicketBooking.dao.BookingInfoDao;
import ir.maktab58.BusTicketBooking.dto.BookingInfoDTO;
import ir.maktab58.BusTicketBooking.entity.BookingInfo;
import ir.maktab58.BusTicketBooking.enums.BusType;

import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BookingInfoService {
    private final BookingInfoDao bookingInfoDao = new BookingInfoDao();

    public BookingInfo getBookingInfo(BookingInfoDTO bookingInfoDTO) {
        return bookingInfoDao.findBookingInfoByDTODetails(bookingInfoDTO);
    }


    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination, Date date) {
        return bookingInfoDao.getListOfBookingInfo(start, numOfRecords, source, destination, date);
    }

    public List getListOfBookingInfo(int start, int numOfRecords, String source, String destination) {
        return bookingInfoDao.getListOfBookingInfo(start, numOfRecords, source, destination);
    }

    public List getListOfFilteredInfoByCompanyName(String source, String destination, int offset, String companyName) {
        return bookingInfoDao.getListOfFilteredBookingInfoByCompanyName(source, destination, offset, companyName);
    }

    public List getListOfFilteredInfoByBusType(String source, String destination, int offset, BusType busTypeEnum) {
        return bookingInfoDao.getListOfFilteredBookingInfoByBusType(source, destination, offset, busTypeEnum);
    }

    public List getListOfFilteredInfoByPriceRange(String source, String destination, int offset, long upBound, long downBound) {
        return bookingInfoDao.getListOfFilteredBookingInfoByPriceRange(source, destination, offset, upBound, downBound);
    }

    public List getListOfFilteredInfoByDepartureHourRange(String source, String destination, int offset, int upBound, int downBound) {
        return bookingInfoDao.getListOfFilteredBookingInfoByDepartureHourRange(source, destination, offset, upBound, downBound);
    }
}
