package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.PatHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatHistory_Repository extends CrudRepository<PatHistory, Integer> {
    @Query("select p from PatHistory p where p.pat_id = ?1")
    List<PatHistory> findByPatId(int id);
}
