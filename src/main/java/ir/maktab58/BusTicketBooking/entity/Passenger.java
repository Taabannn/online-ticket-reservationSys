package ir.maktab58.BusTicketBooking.entity;

import ir.maktab58.BusTicketBooking.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String nationalCode;
    @Column(nullable = false)
    private String phoneNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
