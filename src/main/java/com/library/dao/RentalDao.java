package com.library.dao;

import com.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentalDao extends CrudRepository<Rental, Integer> {
    Rental findByRentalId(int id);

    List<Rental> findAll();
}
