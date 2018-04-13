package com.library.service;

import com.library.dao.PieceDao;
import com.library.dao.ReaderDao;
import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Reader;
import com.library.domain.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbService {

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private PieceDao pieceDao;

    public Reader saveReader(Reader reader) {
        return readerDao.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerDao.findAll();
    }

    public Title saveTitle(Title title) {
        return titleDao.save(title);
    }

    public List<Title> getAllTitles() {
        return titleDao.findAll();
    }

    public Title savePiece(String title) {
        if (titleDao.findByTitle(title) != null) {
            Title book = titleDao.findByTitle(title);
            Piece piece = new Piece("rental");
            book.getPieces().add(piece);
            piece.setTitle(book);
            pieceDao.save(piece);
            return book;
        } else return new Title();
    }

    public List<Piece> getAllPieces() {
        return pieceDao.findAll();
    }

    public Piece setPieceStatus(int id, String status) {
        if (pieceDao.findByPieceId(id) != null) {
            pieceDao.findByPieceId(id).setStatus(status);
            return pieceDao.findByPieceId(id);
        } else return new Piece();
    }

    public Long rentalPiecesOfTitle(String title) {
        if (titleDao.findByTitle(title) != null) {
            return (Long) titleDao.findByTitle(title).getPieces().stream()
                    .filter(piece -> piece.getStatus().equals("rental"))
                    .count();
        } else return new Long(9999);
    }
}
