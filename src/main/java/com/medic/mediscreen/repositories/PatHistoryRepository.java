package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.PatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatHistoryRepository extends MongoRepository<PatHistory, String> {
    List<PatHistory> findByPatId(int id);
}
