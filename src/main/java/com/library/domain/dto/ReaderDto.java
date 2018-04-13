package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {
    private int readerId;
    private String name;
    private String surname;
    private String regDate;
}
