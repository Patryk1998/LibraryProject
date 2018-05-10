package com.library.controller;

import com.library.domain.Piece;
import com.library.domain.Title;
import com.library.domain.dto.*;
import com.library.mapper.Mapper;
import com.library.service.DbService;
import com.library.service.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private DbService dbService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.POST, value = "addReader")
    public List<ReaderDto> addReader(@RequestBody ReaderDto readerDto) {
        dbService.saveReader(mapper.mapReaderDtoToReader(readerDto));
        return getReaders();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllReaders")
    public List<ReaderDto> getReaders() {
        return mapper.mapReaderListToReaderDtoList(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addTitle")
    public List<TitleForList> addTitle(@RequestBody TitleDto titleDto) {
        dbService.saveTitle(mapper.mapTitleDtoToTitle(titleDto));
        return getTitles();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllTitles")
    public List<TitleForList> getTitles() {
        return mapper.mapTitleListToTitleForListList(dbService.getAllTitles());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addPiece")
    public TitleForList addPiece(@RequestParam String title) throws NotFoundException {
         return mapper.mapTitleToTitleForList(dbService.savePiece(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllPieces")
    public List<PieceForList> getPieces() {
       return mapper.mapPiecesListToPieceForListList(dbService.getAllPieces());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changePieceStatus")
    public PieceForList changePieceStatus(@RequestParam int id, String status) throws NotFoundException {
        return mapper.mapPieceToPieceForList(dbService.setPieceStatus(id, status));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentalPiecesOfTitle")
    public Long getRenalPiecesOfTitle(@RequestParam String title) throws NotFoundException {
        return dbService.rentalPiecesOfTitle(title);
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentBook")
    public List<RentalForList> addRental(@RequestBody RentalDto rentalDto) throws NotFoundException {
        dbService.rentBook(mapper.mapRentalDtoToRental(rentalDto));
        return getRentals();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public RentalForList returnBook(@RequestParam int id) throws NotFoundException {
        return dbService.mapToRentalForList(dbService.setReturn(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAlRentals")
    public List<RentalForList> getRentals() {
        return mapper.mapRentalListToRentalForListList(dbService.getAllRentals());
    }


}
