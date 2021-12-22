package ir.maktab58.BusTicketBooking.entity;

import ir.maktab58.BusTicketBooking.enums.BusType;

import javax.persistence.*;

/**
 * @author Taban Soleymani
 */
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private String plateNumber;
}
