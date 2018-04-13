package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentalDto {
    private int rentalId;
    private int pieceId;
    private int readerId;
    private String rentDate;
    private String returnDate;
}
