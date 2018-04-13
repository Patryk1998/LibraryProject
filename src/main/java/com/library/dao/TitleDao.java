package com.library.dao;

import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TitleDao extends CrudRepository<Title, Integer> {
    @Override
    List<Title> findAll();

    Title findByTitle(String title);

}
