package com.library.dao;

import com.library.domain.Piece;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PieceDao extends CrudRepository<Piece, Integer> {

    @Override
    List<Piece> findAll();

    Piece findByPieceId(int id);

}
