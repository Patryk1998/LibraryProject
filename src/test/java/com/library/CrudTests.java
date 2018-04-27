package com.library;

import com.library.dao.PieceDao;
import com.library.dao.ReaderDao;
import com.library.dao.RentalDao;
import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Reader;
import com.library.domain.Rental;
import com.library.domain.Title;
import com.library.domain.dto.PieceDto;
import com.library.domain.dto.ReaderDto;
import com.library.domain.dto.RentalDto;
import com.library.domain.dto.TitleDto;
import com.library.mapper.LocalDateMapper;
import com.library.mapper.Mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTests {

    @Autowired
    private LocalDateMapper localDateMapper;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private PieceDao pieceDao;

    @Autowired
    private RentalDao rentalDao;

    @Test
    public void TestReaderDao() {
        //Given
        ReaderDto readerDto = new ReaderDto(0, "Name", "Surname", LocalDate.now());
        Reader reader = mapper.mapReaderDtoToReader(readerDto);

        //When
        readerDao.save(reader);
        int id = reader.getReaderId();
        Reader reading = readerDao.findByReaderId(id);

        //Then
        Assert.assertEquals(id, reading.getReaderId());
        Assert.assertEquals("Name", reading.getName());
        Assert.assertEquals("Surname", reading.getSurname());
        Assert.assertEquals(reader.getRegDate(), localDateMapper.convertToDatabaseColumn(readerDto.getRegDate()));

        //Clean Up
        readerDao.delete(id);
    }

    @Test
    public void TestTitleDao() {
        //Given
        TitleDto titleDto = new TitleDto(0, "Title", "Author", 2000, new ArrayList<>());
        Title title = mapper.mapTitleDtoToTitle(titleDto);

        //When
        titleDao.save(title);
        int id = title.getTitleId();
        Title reading = titleDao.findByTitle("Title");

        //Then
        Assert.assertEquals(id, reading.getTitleId());
        Assert.assertEquals(title.getTitle(), reading.getTitle());
        Assert.assertEquals(title.getAuthor(), reading.getAuthor());
        Assert.assertEquals(title.getSpendYear(), reading.getSpendYear());

        //Clean Up
        titleDao.delete(id);
    }

    @Test
    public void TestPieceDao() {
        //Given
        PieceDto pieceDto = new PieceDto(0, "status", new Title());
        Piece piece = mapper.mapPieceDtoToPiece(pieceDto);

        //When
        pieceDao.save(piece);
        int id = piece.getPieceId();
        Piece reading = pieceDao.findByPieceId(id);

        //Then
        Assert.assertEquals(piece.getPieceId(), reading.getPieceId());
        Assert.assertEquals(piece.getStatus(), reading.getStatus());

        //Clean Up
        pieceDao.delete(id);
    }

    @Test
    public void TestRentalDto() {
        //Given
        RentalDto rentalDto = new RentalDto(0, 1, 2, LocalDate.now(), LocalDate.now());
        Rental rental = mapper.mapRentalDtoToRental(rentalDto);
        rental.setReturnDate(localDateMapper.convertToDatabaseColumn(LocalDate.now()));

        //When
        rentalDao.save(rental);
        int id = rental.getRentalId();
        Rental reading = rentalDao.findOne(id);

        //Then
        Assert.assertEquals(id, reading.getRentalId());
        Assert.assertEquals(rental.getPieceId(), reading.getPieceId());
        Assert.assertEquals(rental.getReaderId(), reading.getReaderId());
        Assert.assertEquals(rental.getRentDate(), reading.getRentDate());
        Assert.assertEquals(rental.getReturnDate(), reading.getReturnDate());

        //Clean Up
        rentalDao.delete(id);

    }

    @Test
    public void TestRelation() {
        //Given
        TitleDto titleDto = new TitleDto(0, "Title", "Author", 2000, new ArrayList<>());
        Title title = mapper.mapTitleDtoToTitle(titleDto);
        PieceDto pieceDto = new PieceDto(0, "status", title);
        Piece piece = mapper.mapPieceDtoToPiece(pieceDto);
        title.getPieces().add(piece);

        //When
        titleDao.save(title);
        int id = title.getTitleId();
        Title reading = titleDao.findOne(id);

        //Then
        Assert.assertEquals(id, reading.getTitleId());
        Assert.assertEquals(title.getTitle(), reading.getTitle());
        Assert.assertEquals(title.getAuthor(), reading.getAuthor());
        Assert.assertEquals(title.getSpendYear(), reading.getSpendYear());
        Assert.assertEquals(title.getPieces().get(0).getPieceId(), reading.getPieces().get(0).getPieceId());
        Assert.assertEquals(title.getPieces().get(0).getStatus(), reading.getPieces().get(0).getStatus());
        Assert.assertEquals(piece.getTitle().getTitleId(), reading.getTitleId());

        //Clean Up
        titleDao.delete(id);
    }
}
