package com.library.service;

import com.library.dao.PieceDao;
import com.library.dao.ReaderDao;
import com.library.dao.RentalDao;
import com.library.dao.TitleDao;
import com.library.domain.Piece;
import com.library.domain.Reader;
import com.library.domain.Rental;
import com.library.domain.Title;
import com.library.domain.dto.RentalForList;
import com.library.mapper.LocalDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DbService {

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private PieceDao pieceDao;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private LocalDateMapper localDateMapper;

    public Reader saveReader(Reader reader) {
        return readerDao.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerDao.findAll();
    }

    public Title saveTitle(Title title) {
        Piece newPieceToRent = new Piece("rental");
        newPieceToRent.setTitle(title);
        title.getPieces().add(newPieceToRent);
        return titleDao.save(title);
    }

    public List<Title> getAllTitles() {
        return titleDao.findAll();
    }

    public Title savePiece(String nameOfBook) throws NotFoundException {
        Title title = Optional.ofNullable(titleDao.findByTitle(nameOfBook)).orElseThrow(() -> new NotFoundException("The book was not found!"));

        Piece piece = new Piece("rental");
        title.getPieces().add(piece);
        piece.setTitle(title);
        pieceDao.save(piece);
        return title;
    }

    public List<Piece> getAllPieces() {
        return pieceDao.findAll();
    }

    public Piece setPieceStatus(int id, String status) throws NotFoundException {
        Piece piece = Optional.ofNullable(pieceDao.findByPieceId(id)).orElseThrow(() -> new NotFoundException("The piece was not found!"));

        piece.setStatus(status);
        return pieceDao.save(piece);
    }

    public Long rentalPiecesOfTitle(String nameOfBook) throws NotFoundException {
        Title title = Optional.ofNullable(titleDao.findByTitle(nameOfBook)).orElseThrow(() -> new NotFoundException("The book was not found!"));

        return title.getPieces().stream()
                .filter(piece -> piece.getStatus().equals("rental"))
                .count();

    }

    public Rental rentBook(Rental rental) throws NotFoundException {
        Piece piece = Optional.ofNullable(pieceDao.findByPieceId(rental.getPieceId())).orElseThrow(() -> new NotFoundException("The piece was not found!"));
        Reader reader = Optional.ofNullable(readerDao.findByReaderId(rental.getReaderId())).orElseThrow(() -> new NotFoundException("The reader was not found!"));

        if (piece.getStatus().equals("rental")) {
            return rentalDao.save(rental);
        } else throw new NotFoundException("The piece is unavaible!");
    }

    public Rental setReturn(int id) throws NotFoundException {
        Rental rental = Optional.ofNullable(rentalDao.findByRentalId(id)).orElseThrow(() -> new NotFoundException("The rental was not found!"));

        rental.setReturnDate(localDateMapper.convertToDatabaseColumn(LocalDate.now()));
        return rentalDao.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalDao.findAll();
    }

    public RentalForList mapToRentalForList(Rental rental) {
        if (Optional.ofNullable(rental.getReturnDate()).isPresent()) {
            return new RentalForList(rental.getRentalId(), pieceDao.findByPieceId(rental.getPieceId()).getTitle().getTitle(),
                    readerDao.findByReaderId(rental.getReaderId()).getSurname(),
                    localDateMapper.convertToEntityAttribute(rental.getRentDate()),
                    localDateMapper.convertToEntityAttribute(rental.getReturnDate()));
        } else {
            return new RentalForList(rental.getRentalId(), pieceDao.findByPieceId(rental.getPieceId()).getTitle().getTitle(),
                    readerDao.findByReaderId(rental.getReaderId()).getSurname(),
                    localDateMapper.convertToEntityAttribute(rental.getRentDate()),
                    null);
        }
    }
}
