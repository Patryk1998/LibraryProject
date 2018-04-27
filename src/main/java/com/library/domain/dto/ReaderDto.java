package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {
    private int readerId;
    private String name;
    private String surname;
    private LocalDate regDate = LocalDate.now();
}
