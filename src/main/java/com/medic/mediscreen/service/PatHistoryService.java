package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.repositories.Patient_Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
 */

@Service
@Slf4j
public class PatHistoryService {

    @Autowired
    protected Patient_Repository patient_repository;

    public List<PatHistory> getPatHistories(int patId) {
        return patient_repository.findByPatId(patId);
    }

    public void addAPatHistory(int patId, String e) {
        patient_repository.save(new PatHistory(patId, e));

    }
}