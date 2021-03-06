package com.library.domain.dto;

import com.library.domain.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TitleDto {
    private int titleId;
    private String title;
    private String author;
    private int spendYear;
    private List<Piece> pieces = new ArrayList<>();

}
