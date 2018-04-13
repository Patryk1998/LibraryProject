package com.library.mapper;

import com.library.domain.Piece;
import com.library.domain.Reader;
import com.library.domain.Title;
import com.library.domain.dto.PieceDto;
import com.library.domain.dto.ReaderDto;
import com.library.domain.dto.TitleDto;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    public ReaderDto mapReaderToReaderDto(Reader reader) {
        return new ReaderDto(reader.getReaderId(), reader.getName(),
                reader.getSurname(), reader.getRegDate());
    }

    public Reader mapReaderDtoToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getReaderId(), readerDto.getName(),
                readerDto.getSurname(), readerDto.getRegDate());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> list) {
        return list.stream()
                .map(reader -> new ReaderDto(reader.getReaderId(), reader.getName(),
                        reader.getSurname(), reader.getRegDate()))
                .collect(Collectors.toList());

    }

    public TitleDto mapTitleToTitleDto(Title title) {
        return new TitleDto(title.getTitleId(), title.getTitle(),
                title.getAuthor(), title.getSpendDate(), title.getPieces());
    }

    public Title mapTitleDtoToTitle(TitleDto titleDto) {
        return new Title(titleDto.getTitleId(), titleDto.getTitle(),
                titleDto.getAuthor(), titleDto.getSpendDate(), titleDto.getPieces());
    }

    public List<TitleDto> mapToTitleDtoList(List<Title> list) {
        return list.stream()
                .map(title -> new TitleDto(title.getTitleId(), title.getTitle(),
                        title.getAuthor(), title.getSpendDate(), title.getPieces()))
                .collect(Collectors.toList());
    }

    public PieceDto mapPieceToPieceDto(Piece piece) {
        return new PieceDto(piece.getPieceId(), piece.getStatus(), piece.getTitle());
    }

    public Piece mapPieceDtoToPiece(PieceDto pieceDto) {
        return new Piece(pieceDto.getPieceId(), pieceDto.getStatus(), pieceDto.getTitle());
    }

    public List<PieceDto> mapToPieceDtoList(List<Piece> list) {
        return list.stream()
                .map(piece -> new PieceDto(piece.getPieceId(), piece.getStatus(),
                        piece.getTitle()))
                .collect(Collectors.toList());
    }
}
