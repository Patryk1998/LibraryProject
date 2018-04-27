package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "RENTAL_ID")
    private int rentalId;

    @Column(name = "PIECE_ID")
    private int pieceId;

    @Column(name = "READER_ID")
    private int readerId;

    @Column(name = "RENT_DATE")
    private Date rentDate;

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
