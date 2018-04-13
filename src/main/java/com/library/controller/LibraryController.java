package com.library.controller;

import com.library.domain.Piece;
import com.library.domain.Title;
import com.library.domain.dto.PieceDto;
import com.library.domain.dto.ReaderDto;
import com.library.domain.dto.TitleDto;
import com.library.mapper.Mapper;
import com.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return mapper.mapToReaderDtoList(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addTitle")
    public void addTitle(@RequestBody TitleDto titleDto) {
        dbService.saveTitle(mapper.mapTitleDtoToTitle(titleDto));
//        return getTitles();
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getAllTitles")
//    public List<TitleDto> getTitles() {
//        return mapper.mapToTitleDtoList(dbService.getAllTitles());
//    }

    @RequestMapping(method = RequestMethod.POST, value = "addPiece")
    public void addPiece(@RequestParam String title) {
         dbService.savePiece(title);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getAllPieces")
//    public List<PieceDto> getPieces() {
//       return mapper.mapToPieceDtoList(dbService.getAllPieces());
//
//    }

    @RequestMapping(method = RequestMethod.PUT, value = "changePieceStatus")
    public void changePieceStatus(@RequestParam int id, String status) {
        dbService.setPieceStatus(id, status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentalPiecesOfTitle")
    public Long getRenalPiecesOfTitle(String title) {
        return dbService.rentalPiecesOfTitle(title);
    }


}
