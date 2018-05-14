package com.library.mapper;

import com.library.dao.PieceDao;
import com.library.dao.ReaderDao;
import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Reader;
import com.library.domain.Rental;
import com.library.domain.Title;
import com.library.domain.dto.*;
import com.library.service.DbService;
import com.library.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    private LocalDateMapper localDateMapper;

    @Autowired
    private DbService dbService;


    public Reader mapReaderDtoToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getReaderId(), readerDto.getName(),
                readerDto.getSurname(), localDateMapper.convertToDatabaseColumn(readerDto.getRegDate()));
    }

    public List<ReaderDto> mapReaderListToReaderDtoList(final List<Reader> list) {
        return list.stream()
                .map(reader -> new ReaderDto(reader.getReaderId(), reader.getName(),
                        reader.getSurname(), localDateMapper.convertToEntityAttribute(reader.getRegDate())))
                .collect(Collectors.toList());

    }

    public Title mapTitleDtoToTitle(TitleDto titleDto) {
        return new Title(titleDto.getTitleId(), titleDto.getTitle(),
                titleDto.getAuthor(), titleDto.getSpendYear(), titleDto.getPieces());
    }

    public List<TitleForList> mapTitleListToTitleForListList(List<Title> list) {
        return list.stream()
                .map(title -> mapTitleToTitleForList(title))
                .collect(Collectors.toList());
    }

    public List<PieceForList> mapPiecesListToPieceForListList(List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> mapPieceToPieceForList(piece))
                .collect(Collectors.toList());
    }

    public PieceForList mapPieceToPieceForList(Piece piece) {
        return new PieceForList(piece.getPieceId(), piece.getStatus());
    }

    public TitleForList mapTitleToTitleForList(Title title) {
        return new TitleForList(title.getTitleId(), title.getTitle(),
                title.getAuthor(), title.getSpendYear(), mapPiecesListToPieceForListList(title.getPieces()));
    }

    public Piece mapPieceDtoToPiece(PieceDto pieceDto) {
        return new Piece(pieceDto.getPieceId(), pieceDto.getStatus(), pieceDto.getTitle());
    }

    public Rental mapRentalDtoToRental(RentalDto rentalDto) {
        return new Rental(rentalDto.getRentalId(), rentalDto.getPieceId(), rentalDto.getReaderId(), localDateMapper.convertToDatabaseColumn(rentalDto.getRentDate()),
                Date.valueOf("1111-11-1"));
    }

    public List<RentalForList> mapRentalListToRentalForListList(List<Rental> rentals) {
        return rentals.stream()
                .map(rental -> dbService.mapToRentalForList(rental))
                .collect(Collectors.toList());
    }
}
