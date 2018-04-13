package com.library.domain.dto;

import com.library.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PieceDto {
    private int pieceId;
    private String status;
    private Title title;

}
