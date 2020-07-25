package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface Patient_Repository extends CrudRepository<PatHistory, Long> {
    List<PatHistory> findByPatId(int patId);
}
