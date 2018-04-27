package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RentalForList {
    private int rentalId;
    private String nameOfBook;
    private String readerSurname;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
