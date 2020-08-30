package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.repositories.PatHistory_Repository;
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
    protected PatHistory_Repository patHistory_repository;

    public List<PatHistory> getPatHistories(int id) {
        return patHistory_repository.findByPatId(id);
    }

    public void addAPatHistory(int id, PatHistory e) {
        e.setPat_id(id);
        patHistory_repository.save(e);
    }
}