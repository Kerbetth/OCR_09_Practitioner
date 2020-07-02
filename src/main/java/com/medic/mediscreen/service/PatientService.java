package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.Patient;
import com.medic.mediscreen.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
 */

@Service
@Slf4j
public class PatientService {

    @Autowired
    protected PatientRepository patientRepository;

    public void createAPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getAPatient(String email) {
        return patientRepository.findByEmail(email).get();
    }

}