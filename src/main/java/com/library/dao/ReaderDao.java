package com.library.dao;

import com.library.domain.Reader;
import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReaderDao extends CrudRepository<Reader, Integer> {
    @Override
    List<Reader> findAll();

    Reader findByReaderId(int id);

}
