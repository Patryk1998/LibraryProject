package com.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TitleForList {
    private int titleId;
    private String title;
    private String author;
    private int spendYear;
    List<PieceForList> pieces;
}
