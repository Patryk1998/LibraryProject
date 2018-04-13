package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENTAL_ID")
    private int rentalId;

    @Column(name = "PIECE_ID")
    private int pieceId;

    @Column(name = "READER_ID")
    private int readerId;

    @Column(name = "RENT_DATE")
    private String rentDate;

    @Column(name = "RETURN_DATE")
    private String returnDate;

    public Rental(int pieceId, int readerId, String rentDate) {
        this.pieceId = pieceId;
        this.readerId = readerId;
        this.rentDate = rentDate;
    }
}
