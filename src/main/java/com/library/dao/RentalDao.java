package com.library.dao;

import com.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RentalDao extends CrudRepository<Rental, Integer> {
}
