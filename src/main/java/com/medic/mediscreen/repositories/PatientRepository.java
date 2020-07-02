package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);

}
